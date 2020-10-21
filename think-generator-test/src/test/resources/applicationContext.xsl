<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>applicationContext.html</title>
				<style >
					table, th, td
					{
					border: 1px solid black;
					}
				</style>
			</head>
			<body>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="/beans/bean">
	<table><tr><td>userId</td><td>class</td></tr>
		<tr><td><xsl:value-of select="@userId"/></td><td><xsl:value-of select="@class"/></td></tr>
		<tr><td colspan="2">properties</td></tr>
		<tr><td>name</td><td>value</td></tr>
		<xsl:apply-templates/>
	</table>
	</xsl:template>
	<xsl:template match="/beans/bean/property[@name != 'connectProperties']">
		<tr><td><xsl:value-of select="@name"/></td><td><xsl:value-of select="@value"/></td></tr>
	</xsl:template>
	<xsl:template match="/beans/bean/property[@name = 'connectProperties']">
		<tr><td><xsl:value-of select="@name"/></td><td><ol><xsl:apply-templates/></ol></td></tr>
	</xsl:template>
	<xsl:template match="/beans/bean/property/props/prop">
		<li><xsl:value-of select="@key"/>:<xsl:apply-templates/></li>
	</xsl:template>
</xsl:stylesheet>