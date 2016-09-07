require 'java'

class WebSpec  
  
  def self.new
    org.watij.webspec.dsl.WebSpec.module_eval do
      
      def method_missing(methodname, *args, &block)
        WebSpec.debug "WebSpec #{@spec} method_missing: #{methodname}(#{args})"
        tag = find(methodname.to_s)
        if (args.size>0)
          tag = tag.with(args[0])
        end
        tag
      end
      
    end
    
    org.watij.webspec.dsl.Tag.module_eval do      
      
      def method_missing(methodname, *args, &block)
        WebSpec.debug "Tag method_missing: #{methodname}(#{args}) #{args.size}"

        if (methodname != :type && methodname != :id && org.watij.webspec.dsl.Tag.method_defined?(methodname.to_sym))
          return send(methodname, *args, &block)
        end

        if (args.size>0 && methodname.to_s.include?('='))
          value = (args[0].is_a?(String) || args[0].is_a?(Symbol))? "'#{args[0]}'" : args[0]
          set("#{methodname}#{value}")
          return self
        end

        if (args.size==0 && typeof(methodname) == "function")
          return call("#{methodname}()")
        end

        if (respondsTo(methodname))
          if (args.size>0)
            value = (args[0].is_a?(Symbol)) ? args[0].to_s : args[0]
            return with("#{methodname}", value)
          else
            return get(methodname.to_s)
          end
        end

        tag = find(methodname.to_s)
        if (args.size>0)
          tag = tag.with(args[0])
        end
        tag
      end      
      
      def type(type)
        method_missing(:type, type)
      end

      def id(id)
        method_missing(:id, id)
      end

      def [](index)
        at(index)
      end

      def should_have(expectation=nil)
        expectation.nil? ? ShouldHave.new(self) : shouldHave(expectation)
      end
      
    end
    
    org.watij.webspec.dsl.cookie.Cookie.module_eval do
      def method_missing(methodname, *args, &block)
        WebSpec.debug "Cookie method_missing: #{methodname}(#{args}) #{args.size}"
        if (args.size>0)
          return with.send(methodname, *args, &block)
        end
        get.send(methodname, *args, &block)
      end
      def []=(index)
        at(index)
      end
    end
    
    org.watij.webspec.dsl.Find.module_eval do
      def method_missing(methodname, *args, &block) 
        WebSpec.debug "Find method_missing: #{methodname}(#{args}) #{args.size}"
        send(:tag, *args, &block)
      end      
    end
    
    org.watij.webspec.dsl.Property.module_eval do
      def method_missing(methodname, *args, &block)  
        WebSpec.debug "Property method_missing: #{methodname}(#{args}) #{args.size}"
        send(:property, *args, &block)
      end      
    end
    
    org.watij.webspec.dsl.WebSpec.new    
  end

  def self.debug message
    org.watij.webspec.dsl.WebSpec.debug message
  end
  
  def self.method_missing(methodname, *args, &block)    
    WebSpec.debug "Class WebSpec method_missing: #{methodname}(#{args})"
    org.watij.webspec.dsl.WebSpec.send methodname, *args
  end

end


class ShouldHave

  ['==', '!=', '>', '<', '>=', '<='].each do |operator|
    define_method operator.to_sym do |value|
      WebSpec.debug "ShouldHave #{operator}"
      expectation = (value.is_a?(String) || value.is_a?(Symbol)) ? "#{operator}'#{value}'" : "#{operator}#{value}"
      @r_tag.shouldHave("#{@property}#{expectation}")      
      #@r_tag
    end
  end

  def initialize(r_tag)
    WebSpec.debug "NEW ShouldHave"
    @r_tag = r_tag
  end

  def method_missing(methodname, *args, &block)
    WebSpec.debug "ShouldHave method_missing: #{methodname}(#{args}) #{args.size}"
    @property = methodname.to_s
    if (args.size==0)
      return self
    end
    @r_tag.shouldHave(@property, args[0])
    @r_tag
  end

end