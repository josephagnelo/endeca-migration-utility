<?xml version="1.0" encoding="UTF-8"?>

<!-- Copyright Endeca 2008. All Rights Reserved.

No entity shall modify, change, or add to the contents of this file without the
express prior written consent of Endeca, otherwise all warranties are void.

-->

<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform' >

<xsl:output method="xml" indent="yes" doctype-system="dval_refs.dtd" />

<xsl:template match="/" >
	<xsl:element name="DVAL_REFS">
		<xsl:apply-templates select="//DVAL_REF[@INERT or @COLLAPSIBLE]" />
	</xsl:element>
</xsl:template>

<xsl:template match="DVAL_REF">
	<DVAL_REF>
		<xsl:attribute name="ID">
			<xsl:value-of select="DVAL_ID/@ID" />
		</xsl:attribute>
		<xsl:if test="string(@INERT)">
			<xsl:attribute name="INERT">
				<xsl:value-of select="@INERT" />
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="string(@COLLAPSIBLE)">
			<xsl:attribute name="COLLAPSIBLE">
				<xsl:value-of select="@COLLAPSIBLE" />
			</xsl:attribute>
		</xsl:if>
	</DVAL_REF>
</xsl:template>

<xsl:template match="text()" />

</xsl:stylesheet>
