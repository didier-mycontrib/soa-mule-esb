<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:bas="http://service.tp/" 
	xmlns:std="http://standard/">
	
	<xsl:template match="/bas:additionResponse">
		<std:addResponse>
			<return><xsl:value-of select="return" /></return>
		</std:addResponse>
	</xsl:template>
	
	<xsl:template match="/bas:multiplicationResponse">
		<std:multResponse>
			<return><xsl:value-of select="return" /></return>
		</std:multResponse>
	</xsl:template>
	<!--
	<xsl:template match="/bas:addition">
		<std:add>
			<x>
				<xsl:value-of select="a" />
			</x>
			<y>
				<xsl:value-of select="b" />
			</y>
		</std:add>
	</xsl:template>
	-->
	<!--
	<xsl:template match="/bas:multiplication">
		<std:mult>
			<x>
				<xsl:value-of select="a" />
			</x>
			<y>
				<xsl:value-of select="b" />
			</y>
		</std:mult>
	</xsl:template>
	-->
</xsl:stylesheet>