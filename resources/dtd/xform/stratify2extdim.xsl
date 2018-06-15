<?xml version="1.0" encoding="UTF-8"?>

<!-- Copyright Endeca 2008. All Rights Reserved.

No entity shall modify, change, or add to the contents of this file without the
express prior written consent of Endeca, otherwise all warranties are void.

-->

<xsl:stylesheet version='1.0' 
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:stratify='http://www.stratify.com/hierarchy'
	exclude-result-prefixes="stratify">


<xsl:output method="xml" indent="yes" encoding="UTF-8"/>

<xsl:template match="stratify:hierarchy">
	<external_dimensions>
		<xsl:apply-templates select="stratify:topics" />
	</external_dimensions>
</xsl:template>

<xsl:template match="stratify:topics">
	<xsl:apply-templates select="stratify:topic"/>
</xsl:template>

<xsl:template match="stratify:topic">
  <node>
	  <xsl:attribute name="name">
		  <xsl:value-of select="@name"/>
		</xsl:attribute>
		<xsl:attribute name="id">
		  <xsl:value-of select="@id"/>
		</xsl:attribute>
		<xsl:if test="@id != @parentID">
		  <xsl:attribute name="parent">
			  <xsl:value-of select="@parentID"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="classify">false</xsl:attribute>
		<synonym>
		  <xsl:attribute name="name">
			  <xsl:value-of select="@id"/>
			</xsl:attribute>
			<xsl:attribute name="classify">true</xsl:attribute>
			<xsl:attribute name="search">false</xsl:attribute>
		</synonym>
	</node>
</xsl:template>

</xsl:stylesheet>
