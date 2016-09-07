$count = 0
$windowTitle = $CmdLine[1]
$textToSend = $CmdLine[2]
While NOT WinActive($windowTitle)
    If $count > 40 Then ExitLoop
    WinActivate($windowTitle)
    Sleep(250)
    $count = $count + 1
WEnd
If WinActive($windowTitle) Then
    Sleep(750)
    ControlSend($windowTitle, "", "[CLASS:Edit; INSTANCE:1]", $textToSend)
    ControlClick($windowTitle, "", "[CLASS:Button; INSTANCE:1]")
EndIf