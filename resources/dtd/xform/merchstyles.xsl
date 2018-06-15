<?xml version="1.0" encoding="UTF-8"?>

<!-- Copyright Endeca 2008. All Rights Reserved.

No entity shall modify, change, or add to the contents of this file without the
express prior written consent of Endeca, otherwise all warranties are void.

-->

<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform' >

<xsl:output method="xml" indent="yes" doctype-system="merchstyles.dtd" />

<xsl:template match="/" >
	<MERCHSTYLES>
		<xsl:apply-templates select="//STYLE" />
	</MERCHSTYLES>
</xsl:template>

<xsl:template match="STYLE">
	<xsl:copy-of select="." />
</xsl:template>

<xsl:template match="text()" />

</xsl:stylesheet>
