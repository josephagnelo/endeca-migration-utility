<?xml version="1.0" encoding="utf-8"?>

<!-- Copyright Endeca 2008. All Rights Reserved.

No entity shall modify, change, or add to the contents of this file without the
express prior written consent of Endeca, otherwise all warranties are void.

-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<!-- we need to preserve the doctype, but xstl can't access this, so we hardcode merch_rule_group.dtd... -->
<xsl:output method="xml" doctype-system="merch_rule_group.dtd" />
<!-- copy all elements and their attributes-->
<xsl:template match="* | @*">
	<xsl:copy>
		<xsl:copy-of select="@*"/>
		<xsl:apply-templates/>
	</xsl:copy>
</xsl:template>
	<!-- strip out the MERCH_RULE elements who have a PROP specifying the workflow state as INACTIVE -->
	<xsl:template match="MERCH_RULE[PROP[@NAME='endeca.internal.workflow.state'][PVAL='INACTIVE']]" />
</xsl:stylesheet>
