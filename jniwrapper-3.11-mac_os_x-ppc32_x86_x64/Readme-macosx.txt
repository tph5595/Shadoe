--------------------------------------------------------------------------------

JNIWrapper v3.11 for MacOS X

Readme and Quick Manual

Copyright (c) 2000-2014 TeamDev Ltd. All Rights Reserved.
--------------------------------------------------------------------------------

CONTENTS
1. Release Notes
2. Getting Started
3. Support
4. Purchase Information
5. Legal Information
6. Contact

JNIWrapper  is  the  technology  that  eliminates  difficulties  in working with
native  code from Java(TM) programs  using  standard JNI (Java Native Interface)
approach.

================================================================================
1. RELEASE NOTES

Release notes are available online http://links.teamdev.com/jniwrapper-release-notes

API  differences between JNIWrapper 3.10 and 3.11 
versions are available online 
at http://www.teamdev.com/downloads/jniwrapper/javadoc/changes.html

================================================================================
2. GETTING STARTED

System Requirements
-------------------
The following are general requirements  for running JNIWrapper on any  supported
platform:

   Java 2 compatible SDK or Runtime Environment version 1.3.x, 1.4.x, 1.5.x or 1.6.x
   Note: JNIWrapper  memory  consumption  is  negligible  compared  to  that  of 
         Java2 runtime.   
   
   MacOS X
   ---------
   JNIWrapper for MacOS X supports MacOS X 10.3.x and higher. 
   
Package Contents and Installation
---------------------------------
JNIWrapper package consists of the following main files:

lib/jniwrap3.11.jar - Library JAR file
lib/jniwrap-generator-3.11.jar - Codegen Library JAR file
bin/libjniwrap.jnilib - Native code library for MacOS X
docs/ - includes JNIWrapper documentation.
Readme.txt  - this file
Runtime.txt - describes how to deploy applications, which use JNIWrapper
License agreement.txt - license agreement
License installation.txt - JNIWrapper license installation instructions
samples/ - samples, which demonstrate basic features of JNIWrapper

You can select a package type that fits your needs best on  JNIWrapper Download
page (http://www.teamdev.com/jniwrapper/).

All the files have to be placed in the appropriate locations. Please read below
for more details about the product installation instructions.

   Installing Library JAR File
   ---------------------------
JNIWrapper JAR file should be on the program's class path.
Library file  can be  also placed  on the  boot class  path or  in the extension
directory of Java runtime, but this is not required.

   Installing Native Code Library
   ------------------------------
   
JNIWrapper native library can be placed virtually anywhere. Its actual  location
should take into account that Java code must find  the JNILIB to load. It can be
placed  somewhere  within program's  library  search path. Users  can add search 
path to the default library loader used by JNIWrapper or even write a custom one 
that searches for  native code in  a predefined location. Since  JNIWrapper v3.0
native  libraries  can  be  put into  any  jar  library  from the  application's
classpath (but not into the META-INF folder).

   MacOS X
   ---------
JNIWrapper  native  library libjniwrap.jnilib  can  be placed  into  the  system
directory  /usr/lib.  You  can  also  specify  custom  library  path  using  the 
LD_LIBRARY_PATH or DYLD_LIBRARY_PATH environement variable. System library paths
are listed  in the /etc/ld.so.conf file.

================================================================================
3. SUPPORT

If  you  encounter any problems  or  have questions regarding our product please
first check the documents listed  below. The answer to your question may already
present there.

 - Installation instructions
 - User's Guide
 - Troubleshooting section (http://links.teamdev.com/jniwrapper-troubleshooting)
 - Frequently Asked Questions (FAQ)(http://links.teamdev.com/jniwrapper-faq)
 - JNIWrapper Forum (http://links.teamdev.com/jniwrapper-forum)

If none of above sources contains  the information that you need please contact us 
via support forum or support email addresses. For directions please visit 
(http://www.teamdev.com/support/).

Reporting Problems
------------------
Should you experience a problem or find a bug, please submit us an issue  through 
our support forum or support email addresses. For directions please visit 
(http://support.teamdev.com/#jniwrapper). The form will help you provide all necessary 
information.

================================================================================
4. PURCHASE INFORMATION

To obtain the latest version of JNIWrapper and to receive up-to-date
information visit: http://www.teamdev.com/jniwrapper/

To purchase JNIWrapper online, please point your browser to:
http://www.teamdev.com/store/jniwrapper/

================================================================================
5. LEGAL INFORMATION

Development and Runtime License
-------------------------------
Having purchased JNIWrapper license you receive the license pack  that  includes
two types of license keys:
 - development key: to be installed at each of  your  developers'  workstations.
   You can install development licenses on the number of computers  not  greater
   than the number of licenses purchased.
 - runtime  key:  it  has  to  be  deployed  with  your   application   to   the
   customers. There is no  limit  for  client  installations  number  with  this
   runtime license key.

Evaluation License
------------------
The evaluation key received upon request on our web site grants  you  permission
to use JNIWrapper for evaluation purposes during the period of 30 days.

License Agreement
-----------------
Please be sure to read carefully the license agreement  document  supplied  with 
the product in the license agreement.txt file.

Acknowledgements
----------------
This product includes software  developed  by  the  Apache  Software  Foundation 
(http://www.apache.org/).

================================================================================
6. CONTACT

TeamDev Ltd.
E-mail  : info@teamdev.com, or
Phone   : 1-425-223-3079 (US),
          38-057-766-0163 (UA)
          Monday - Friday, from 11 a.m. to 7 p.m. (GMT+2)
Web     : http://www.teamdev.com
