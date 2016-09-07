["java", "#{File.dirname(__FILE__)}/../ruby/src/web_spec", "#{File.dirname(__FILE__)}/../java/dist/webspec.jar"].concat(Dir["#{File.dirname(__FILE__)}/../lib/*.jar"]).each { |file| require file }
WebSpec.extra_careful_pause_until_ready false
@spec = WebSpec.new