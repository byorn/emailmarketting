emailmarketting
===============

A Single Page Web App for the Email Marketing domain

<b>Prerequisites</b>
<ul>
<li>Install XAMP or LAMP </li>
<li>Create a database called ehammer</li>
<li>Run the SQL Script to create the tables.</li>
<li>Make sure the file paths in the Web.xml exist.</li>
</ul>

<b>Technical Features To Notice</b>
<ul>
<li>Single Page Web App with JSF Prime Faces and JQuery </li>
<li>Achieving connection pooling with Hibernate C3PO. </li>
<li>Achieving Asynchronous Email Sending with Quartz as this product will be initially deployed on a Cheap Tomcat Hosting Environment that does not support the Java EE 5 Web Profile or Asynchronous Servlets.</li>
<li>Configured Log 4J.</li>
</ul>

<b>Improvements to be made</b>
<ul>
<li>Migrate to a Maven or Gradle  </li>
<li>To be able to host in JBoss </li>
<li>To use Postgres as the Database </li>
<li>To deploy to Open Shift. </li>
</ul>

Any volunteers for the project is most welcome!
