["java", "#{File.dirname(__FILE__)}/../src/web_spec", "#{File.dirname(__FILE__)}/../../java/dist/webspec.jar"].concat(Dir["#{File.dirname(__FILE__)}/../../lib/*.jar"]).each { |file| require file }

def should_be_true(bool)
  bool ? bool : raise("should have been true")
end

def test_html(spec)
  
  spec.instance_eval do
    
    #source
#    should_be_true(source.match("<html>") || source.match("<HTML>"));
#    should_be_true(html.innerHTML.match("<head>") || html.innerHTML.match("<HEAD>"));
#    open "about:blank"
#    source "<html><head><title>My New Title</title></head><body><input type='text' value=''></body></html>"
#    title("My New Title").should_exist
#    input.value = "New Html"
#    input.should_have.value == "New Html"
    
    #iframe
    iframe.find.title.should_have "I am an iframe"
    
    #show hide    
    should_be_true visible?
    hide
    pause 500
    should_be_true !visible?
    show
    pause 500
    should_be_true visible?        
    
    #frames
    
    open("file:///Users/bknorr/Dev/WebSpec/html/topframe.html")
    find_with_id("f1").find.title.should_have("I am inside frame 1")
    find_with_id("f2").find.title.should_have("I am inside frame 2")
    open("file:///Users/bknorr/Dev/WebSpec/html/test.html")
    
    #cookies
    
    cookie.all.delete
    should_be_true(cookie.all.length==0)
    cookie.create("http://www.watij.com/", "coolName", "coolValue", nil, nil, java.lang.System.currentTimeMillis + 3600000, false, false, false)
    should_be_true(cookie.all.length == 1)
    should_be_true("coolName" == cookie.name)
    should_be_true("coolValue" == cookie.value)
    cookie.all.delete
    should_be_true(cookie.all.length==0)    
    
    #file input
    
    if (ie?)
      puts record
      record.file.set("C:\\Dev\\WebSpec\\autoit\\file_ok.exe").ok
      input.name("file_input").click
    end
    
    
    #open many windows
    
    should_be_true(browser_count == 1)
    
    5.times do
        browser.open("file:///C:/Dev/WebSpec/html/test.html")
    end
    should_be_true(browser_count == 6)
    5.times do
      browser(1).close
    end
    should_be_true(browser_count == 1)
    browser(0)
    
    
    #popup browser

    a("Popup").click

    browser 1

    popup_result = find_with_id("result");
    popup_result.should_have.value == 'empty'

    popup_result.value = 'I have set the popup'
    popup_result.should_have.value == 'I have set the popup'

    input.type("button").click
    browser 0    

    result = find_with_id("result")
    result.should_have.value == 'empty'

    #Alert
  
    3.times do |i|
        record.alert.should_have("alert#{i}").ok
        a("alert#{i}").click
    end
  
    #Confirm
  
    record.confirm.ok
    a("confirm0").click
    result.should_have.value == 'true'
  
    record.confirm.cancel
    a("confirm0").click
    result.should_have.value == 'false'
  
  
    #Prompt    
    
    record.prompt.set("myvalue0").ok
    a.innerText("prompt0").click
    result.should_have.value == "myvalue0"
  
    record.prompt.set(nil).ok
    a("prompt0").click
    result.should_have.value == "default0"
  
    record.prompt.set("somevalue").cancel
    a("prompt0").click
    result.should_have.value == (ie? ? "null" : "somevalue")    
    
    #Buttons
  
    3.times do |i|
        input.name("button#{i}").click
        result.should_have.value == "button#{i}"
    end
  
    3.times do |i|
        input.type(:button)[i].click
        result.should_have.value == "button#{i}"
    end
    
    input.type("image").click
    result.should_have.value == "image"

    #TextFields
  
    textField = input.name('textfield0')
    textField.value = "textfield0"
    textField.onblur()
    result.should_have.value == "textfield0"
  
  
    #Checkboxes
  
    checkbox0 = input.name('checkbox0')
    checkbox0.should_have.checked==false
    checkbox0.checked=true
    checkbox0.should_have.checked==true
  
    checkbox1 = input.name(:checkbox1)
    checkbox1.should_have.checked==true
    checkbox1.click
    checkbox1.should_have.checked==false
    result.should_have.value=='false'
    checkbox1.click
    checkbox1.should_have.checked==true
    result.should_have.value=='true'
  
  
    #Radios
  
    radio0 = input.name(:radio)[0]
    radio1 = input.name(:radio)[1]
  
    radio0.should_have.value=='radio0'
    radio0.should_have.checked==true
    radio1.should_have.value=='radio1'
    radio1.should_have.checked==false
  
    radio1.click
    radio1.should_have.checked==true
    radio0.should_have.checked==false
    result.should_have.value=="radio1"
  
    radio0.click
    radio0.should_have.checked==true
    radio1.should_have.checked==false
    result.should_have.value=="radio0"
  
    #Select
    
    selectTag = self.select    
    option0 = option[0]
    option1 = option[1]
    option2 = option[2]
  
    selectTag.should_have.value=="select0"    
    option0.should_have.selected==true
    option0.should_have "option0"
    option1.should_have.selected==false
    option1.should_have "option1"
    option2.should_have.selected==false
    option2.should_have "option2"
  
    option1.selected=true
    option0.should_have.selected==false
    option1.should_have.selected==true
    option2.should_have.selected==false
    selectTag.should_have.value=="select1"
    selectTag.onchange()
    result.should_have.value=="select1"
  
    selectTag.value="select2"
    option0.should_have.selected==false
    option1.should_have.selected==false
    option2.should_have.selected==true
    selectTag.should_have.value=="select2"
    selectTag.onchange()
    result.should_have.value=="select2"


    #Oh Snap
#    if mozilla?
#        file = java.io.File.new("snap_webspec.png")
#        assertFalse(file.exists)
#        snap("snap_webspec.png");
#        file = new java.io.File("snap_webspec.png");
#        assertTrue(file.exists());
#        file.delete();
#    end


    
    #tables

    out_table = table
    in_table = out_table.table

    out_table.tr.all.should_have(6)
    out_table.child.tbody.child.tr.all.should_have(4)
    in_table.tr.all.should_have(2)
    in_table.child.tbody.child.tr.all.should_have(2)

    3.times do |i|
        tr = out_table.tr[i]
        (i+1).times do |j|
            td = tr.td[j]
            td.should_have("out-r#{i}-c#{j}")
            td.parent.table.all.should_have(1)
        end
    end

    2.times do |i|
        tr = in_table.tr[i]
        (i+1).times do |j|
            td = tr.td[j]
            td.should_have("in-r#{i}-c#{j}")
            td.parent.table.all.should_have(2)
        end
    end

  end

end

WebSpec.debug true
WebSpec.click_then_pause_until_ready false
WebSpec.extra_careful_pause_until_ready false
WebSpec.show_navigation_bar true
WebSpec.webspec_home "C:\\Dev\\WebSpec"
test_html WebSpec.new.mozilla.open("file:///Users/bknorr/Dev/WebSpec/html/test.html")
#test_html WebSpec.new.mozilla.open("file:///C:/Dev/WebSpec/html/test.html")