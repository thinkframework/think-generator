<?xml version="1.0" encoding="utf-8" ?>
<GSPModel GSPModelNode="True" DataFormatVersion="2.5.12" BizVersion="" SimpleDataIntf="False" xmlns:a="attribute" xmlns:c="collection" xmlns:o="object">
    <a:Version>2.5.12</a:Version>
    <a:Name>GSPModel</a:Name>
    <a:Mode>2</a:Mode>
    <c:Databases>
        <o:Database>
            <a:Name>BusinessDB</a:Name>
            <c:Tables>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>UnitDict</a:Name>
                        <a:DisplayName>单位字典</a:DisplayName>
                        <a:Catalog>公共字典</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ID</a:FieldName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>400230,&apos;m&apos;</o:Record>
                            <o:Record>400231,&apos;m2&apos;</o:Record>
                            <o:Record>400232,&apos;m3&apos;</o:Record>
                            <o:Record>400233,&apos;t&apos;</o:Record>
                            <o:Record>400234,&apos;kg&apos;</o:Record>
                            <o:Record>400235,&apos;km&apos;</o:Record>
                            <o:Record>400236,&apos;个&apos;</o:Record>
                            <o:Record>400237,&apos;工日&apos;</o:Record>
                            <o:Record>400238,&apos;台班&apos;</o:Record>
                            <o:Record>400239,&apos;项&apos;</o:Record>
                            <o:Record>400240,&apos;座&apos;</o:Record>
                            <o:Record>400241,&apos;组&apos;</o:Record>
                            <o:Record>400242,&apos;元&apos;</o:Record>
                            <o:Record>400243,&apos;套&apos;</o:Record>
                            <o:Record>400244,&apos;台&apos;</o:Record>
                            <o:Record>400245,&apos;盘&apos;</o:Record>
                            <o:Record>400246,&apos;辆&apos;</o:Record>
                            <o:Record>400247,&apos;块&apos;</o:Record>
                            <o:Record>400248,&apos;件&apos;</o:Record>
                            <o:Record>400249,&apos;把&apos;</o:Record>
                            <o:Record>400250,&apos;盒&apos;</o:Record>
                            <o:Record>400251,&apos;根&apos;</o:Record>
                            <o:Record>400252,&apos;系统&apos;</o:Record>
                            <o:Record>400253,&apos;米&apos;</o:Record>
                            <o:Record>400254,&apos;平方米&apos;</o:Record>
                            <o:Record>400255,&apos;立方米&apos;</o:Record>
                            <o:Record>400256,&apos;吨&apos;</o:Record>
                            <o:Record>400257,&apos;千克&apos;</o:Record>
                            <o:Record>400258,&apos;千米&apos;</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>BIMFHL</a:Name>
                        <a:AliasName>wztj_bimfhl</a:AliasName>
                        <a:DisplayName>BIM复核量</a:DisplayName>
                        <a:Catalog>BIM模型量</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>BIMFHLID</a:FieldName>
                                <a:AliasName>wztj_bimfhl_id</a:AliasName>
                                <a:DisplayName>复核量ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SpecialtyName</a:FieldName>
                                <a:AliasName>specialty_name</a:AliasName>
                                <a:DisplayName>专业</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ElementTypeID</a:FieldName>
                                <a:AliasName>element_type_id</a:AliasName>
                                <a:DisplayName>构件类型</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SystemSpec</a:FieldName>
                                <a:AliasName>system_spec</a:AliasName>
                                <a:DisplayName>规格</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>QuantityType</a:FieldName>
                                <a:AliasName>quantity_type</a:AliasName>
                                <a:DisplayName>工程量类型</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Unit</a:FieldName>
                                <a:DisplayName>单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Quantity</a:FieldName>
                                <a:DisplayName>工程量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Stastics</a:FieldName>
                                <a:DisplayName>统计</a:DisplayName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BQItemID</a:FieldName>
                                <a:AliasName>bqitem_id</a:AliasName>
                                <a:DisplayName>清单</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>NormItemID</a:FieldName>
                                <a:AliasName>norm_item_id</a:AliasName>
                                <a:DisplayName>资源ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>SegmentID</a:FieldName>
                                <a:AliasName>segment_id</a:AliasName>
                                <a:DisplayName>流水段</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>Segment</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SegmentGroupID</a:FieldName>
                                <a:AliasName>segment_group_id</a:AliasName>
                                <a:DisplayName>流水段分组</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ElementTypeName</a:FieldName>
                                <a:AliasName>element_type_name</a:AliasName>
                                <a:DisplayName>构建类型名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ResID</a:FieldName>
                                <a:DisplayName>材料ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQTitle</a:Name>
                        <a:AliasName>wztj_geqtitle</a:AliasName>
                        <a:DisplayName>分部分项标题</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>TitleID</a:FieldName>
                                <a:AliasName>wztj_geqtitle_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>PID</a:FieldName>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>GEQTitle</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Description</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>Amount</a:FieldName>
                                <a:DisplayName>合价</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>1</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:DisplayName>备注</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BGColor</a:FieldName>
                                <a:DisplayName>背景色</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>IsVisible</a:FieldName>
                                <a:AliasName>is_visible</a:AliasName>
                                <a:DisplayName>过滤可见性</a:DisplayName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>False</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>StageDivisionID</a:FieldName>
                                <a:AliasName>stage_division_id</a:AliasName>
                                <a:DisplayName>阶段划分ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>StageDivision</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                                <a:DefaultExpr>1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SrcID</a:FieldName>
                                <a:AliasName>src_id</a:AliasName>
                                <a:DisplayName>成本科目企业端ID</a:DisplayName>
                                <a:Remark>按成本科目方式编制时使用</a:Remark>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MarketPreTaxAmount</a:FieldName>
                                <a:AliasName>market_pre_tax_amount</a:AliasName>
                                <a:DisplayName>不含税统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQResource.MarketPreTaxRate[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MarketTaxAmount</a:FieldName>
                                <a:AliasName>market_tax_amount</a:AliasName>
                                <a:DisplayName>统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQResource.MarketTaxRateAmount[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>MXQuantity</a:FieldName>
                                <a:DisplayName>模型量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TJQuantity</a:FieldName>
                                <a:DisplayName>统计量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>GRTotalJE</a:FieldName>
                                <a:DisplayName>当月购入统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZGZZResource.GRJE[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>AccumulatedGRTotalJE</a:FieldName>
                                <a:DisplayName>累积购入统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZGZZResource.GRJESUM[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TXTotalJE</a:FieldName>
                                <a:DisplayName>本月摊销统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZGZZResource.TXJE[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>AccumulatedTXTotalJE</a:FieldName>
                                <a:DisplayName>累计摊销统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZGZZResource.TXJESUM[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>PayForTotalJE</a:FieldName>
                                <a:DisplayName>赔偿统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZLZZResource.PayForAmount[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>AmountTotalMoney</a:FieldName>
                                <a:DisplayName>合计统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(GEQZLZZResource.AmountMoney[(TitleID = ?TitleID)].SUM, PrecisionOption.TitleAmount)</a:CalcExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQResource</a:Name>
                        <a:AliasName>wztj_geqresource</a:AliasName>
                        <a:DisplayName>资源汇总</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:RecordCustomMacroDefs>
                            <o:CustomMacroDef index="0">
                                <a:Code>MXL</a:Code>
                                <a:Description>BIM模型量</a:Description>
                                <a:ValueExpr>BIMFHL.Quantity[(Stastics = true) and (ResID=?ResID)].SUM</a:ValueExpr>
                            </o:CustomMacroDef>
                        </c:RecordCustomMacroDefs>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ResID</a:FieldName>
                                <a:AliasName>wztj_geqresource_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GGXH</a:FieldName>
                                <a:DisplayName>规格型号</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>DW</a:FieldName>
                                <a:DisplayName>单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>UnitDict</a:LookupTableName>
                                <a:LookupResult>Name</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>MarketPreTaxRate</a:FieldName>
                                <a:AliasName>market_pre_tax_rate</a:AliasName>
                                <a:DisplayName>不含税市场价</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TaxRatio</a:FieldName>
                                <a:AliasName>tax_ratio</a:AliasName>
                                <a:DisplayName>税率</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>cbkmID</a:FieldName>
                                <a:AliasName>cbkm_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>cbkm</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>name</a:LookupDescription>
                                <a:MasterTableName>cbkm</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="6">
                                <a:FieldName>MarketTaxRate</a:FieldName>
                                <a:AliasName>market_tax_rate</a:AliasName>
                                <a:DisplayName>含税市场价</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:NullExpr>FRound(MarketPreTaxRate*(1+TaxRatio/100),PrecisionOption.ResRate)</a:NullExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Quantity</a:FieldName>
                                <a:DisplayName>供应商</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MarketPreTaxRateAmount</a:FieldName>
                                <a:AliasName>market_pre_tax_rate_amount</a:AliasName>
                                <a:DisplayName>不含税市场价合价</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>FRound(MarketPreTaxRate*XPQuantity,PrecisionOption.Usage)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MarketTaxRateAmount</a:FieldName>
                                <a:AliasName>market_tax_rate_amount</a:AliasName>
                                <a:DisplayName>含税市场价合价</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>FRound(MarketTaxRate*XPQuantity,PrecisionOption.Usage)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>_DisplayCode</a:FieldName>
                                <a:AliasName>geq_display_code</a:AliasName>
                                <a:Remark>在企业端表中的DisplayCode</a:Remark>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TaxAmount</a:FieldName>
                                <a:AliasName>tax_amount</a:AliasName>
                                <a:DisplayName>税额</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>FRound(MarketTaxRateAmount-MarketPreTaxRateAmount,PrecisionOption.Usage)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>CompareKey</a:FieldName>
                                <a:AliasName>compare_key</a:AliasName>
                                <a:DisplayName>唯一标识</a:DisplayName>
                                <a:DataType>10</a:DataType>
                                <a:CalcExpr>Code+&apos;_&apos;+Name+&apos;_&apos;+GGXH+&apos;_&apos;+DW+&apos;_&apos;+FloatToStr(MarketPreTaxRate)+&apos;_&apos;+FloatToStr(MarketTaxRate)+&apos;_&apos;+FloatToStr(TaxRatio)+&apos;_&apos;+UInt64ToStr(cbkmID)+&apos;_&apos;+IntToStr(ResourceType)+&apos;_&apos;+UInt64ToStr(StageDivisionID)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>Amount</a:FieldName>
                                <a:DisplayName>合价</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(IIF(Option.EstiByPreTaxRate, MarketPreTaxRate*XPQuantity, MarketTaxRate*XPQuantity), PrecisionOption.Usage)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MarketRate</a:FieldName>
                                <a:AliasName>market_rate</a:AliasName>
                                <a:DisplayName>单价</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(IIF(Option.EstiByPreTaxRate,MarketPreTaxRate,MarketTaxRate),PrecisionOption.Usage)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BGColor</a:FieldName>
                                <a:DisplayName>颜色过滤</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>StageDivisionID</a:FieldName>
                                <a:AliasName>stage_division_id</a:AliasName>
                                <a:DisplayName>阶段划分ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>StageDivision</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                                <a:DefaultExpr>1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>AddNum</a:FieldName>
                                <a:AliasName>add_num</a:AliasName>
                                <a:DisplayName>补充材料流水码</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Entirety</a:FieldName>
                                <a:DisplayName>单体（楼栋）</a:DisplayName>
                                <a:StrLength>1024</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FloorName</a:FieldName>
                                <a:AliasName>floor_name</a:AliasName>
                                <a:DisplayName>流水段/部位</a:DisplayName>
                                <a:StrLength>1024</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>MXQuantity</a:FieldName>
                                <a:DisplayName>模型量</a:DisplayName>
                                <a:Hint>模型量</a:Hint>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>IIF(DataSource=3, BIMFHL.Quantity[(Stastics = true) and (ResID=?ResID)].SUM, IIF(DataSource=2, IIF((not IsNull(GEQBQOriginFHL)) and (GEQBQOriginFHL&lt;&gt;0), BIMFHL.Quantity[(Stastics = true) and (ResID=?ResID)].SUM / GEQBQOriginFHL * OriginFHL, 0), 0))</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Supplier</a:FieldName>
                                <a:DisplayName>供应商名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FBCompany</a:FieldName>
                                <a:DisplayName>分包单位名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>DiffQuantity</a:FieldName>
                                <a:AliasName>diff_quantity</a:AliasName>
                                <a:DisplayName>量差</a:DisplayName>
                                <a:Hint>量差</a:Hint>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>MXQuantity - XPQuantity</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>TitleID</a:FieldName>
                                <a:AliasName>title_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:MasterTableName>GEQTitle</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>JZFS</a:FieldName>
                                <a:DisplayName>浇筑方式</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>1</a:DefaultExpr>
                                <a:EnumNames>1=|2=地泵|3=汽泵</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>ResourceType</a:FieldName>
                                <a:AliasName>resource_type</a:AliasName>
                                <a:DisplayName>类别</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>2</a:DefaultExpr>
                                <a:EnumNames>1=人|2=材|3=机|4=专</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BIMLinked</a:FieldName>
                                <a:AliasName>bim_linked</a:AliasName>
                                <a:DisplayName>关联</a:DisplayName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="4">
                                <a:FieldName>QuantityExpr</a:FieldName>
                                <a:AliasName>quantity_expr</a:AliasName>
                                <a:DisplayName>工程量表达式</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>XPQuantity</a:FieldName>
                                <a:DisplayName>小票量/统计量</a:DisplayName>
                                <a:Remark>在混凝土作为小票量，在钢筋和库存材料作为统计量</a:Remark>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>FRound(IIF(QuantityHasEdited, IIF(ExtractNumeric(DW) = 0, QuantityExpr, QuantityExpr / ExtractNumeric(DW)), MXQuantity), PrecisionOption.NormQuantity)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>DataSource</a:FieldName>
                                <a:AliasName>data_source</a:AliasName>
                                <a:DisplayName>数据来源</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=FromNew|1=FromQuery|2=FromGEQ|3=FromGEQTJ</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OriginFHL</a:FieldName>
                                <a:AliasName>origin_fhl</a:AliasName>
                                <a:DisplayName>原始复核量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BIMFHLID</a:FieldName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>IsDeltaRes</a:FieldName>
                                <a:AliasName>is_delta_res</a:AliasName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>compareKey2</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>IsAlone</a:FieldName>
                                <a:DataType>1</a:DataType>
                                <a:CalcExpr>IIF(DataSource = 3,BIMFHL.BIMFHLID[(ResID = ?ResID) and (Stastics = true)].Exist,true)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>QuantityHasEdited</a:FieldName>
                                <a:AliasName>quantity_has_edited</a:AliasName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GEQBQOriginFHL</a:FieldName>
                                <a:AliasName>geq_bq_origin_fhl</a:AliasName>
                                <a:DisplayName>所属清单原始复合量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQZGZZResource</a:Name>
                        <a:AliasName>wztj_geqzgzzresource</a:AliasName>
                        <a:DisplayName>自购周转材料</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>GEQZGZZResourceID</a:FieldName>
                                <a:AliasName>wztj_geqzgzzresource_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>cbkmID</a:FieldName>
                                <a:AliasName>cbkm_id</a:AliasName>
                                <a:DisplayName>成本科目</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:MasterTableName>cbkm</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>TitleID</a:FieldName>
                                <a:AliasName>title_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:MasterTableName>GEQTitle</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>ResourceType</a:FieldName>
                                <a:AliasName>resource_type</a:AliasName>
                                <a:DisplayName>类别</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>2</a:DefaultExpr>
                                <a:EnumNames>1=人|2=材|3=机|4=专</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GGXH</a:FieldName>
                                <a:DisplayName>规格型号</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>DW</a:FieldName>
                                <a:DisplayName>单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>UnitDict</a:LookupTableName>
                                <a:LookupResult>Name</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GRQuantity</a:FieldName>
                                <a:DisplayName>当月购入数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GRJE</a:FieldName>
                                <a:DisplayName>当月购入金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>GRQuantitySUM</a:FieldName>
                                <a:AliasName>grquantity_sum</a:AliasName>
                                <a:DisplayName>累计购入数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(GRQuantity+LastGRQuantity,6)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>GRJESUM</a:FieldName>
                                <a:DisplayName>累计购入金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(GRJE+LastGRJE,2)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TXQuantity</a:FieldName>
                                <a:DisplayName>本月摊销用量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TXJE</a:FieldName>
                                <a:DisplayName>本月摊销金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TXQuantitySUM</a:FieldName>
                                <a:AliasName>txquantity_sum</a:AliasName>
                                <a:DisplayName>累计摊销数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(LastTXQuantity+TXQuantity,6)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TXJESUM</a:FieldName>
                                <a:DisplayName>累计摊销金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(TXJE+LastTXJE,2)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>LastGRQuantity</a:FieldName>
                                <a:AliasName>last_grquantity</a:AliasName>
                                <a:DisplayName>往期累积购入数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>LastGRJE</a:FieldName>
                                <a:AliasName>last_grje</a:AliasName>
                                <a:DisplayName>往期累积购入金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>LastTXQuantity</a:FieldName>
                                <a:AliasName>last_txquantity</a:AliasName>
                                <a:DisplayName>往期摊销数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>LastTXJE</a:FieldName>
                                <a:AliasName>last_txje</a:AliasName>
                                <a:DisplayName>往期摊销金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>DataSource</a:FieldName>
                                <a:DisplayName>数据来源</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=FromNew|1=FromQuery|2=FromGEQ|3=FromPrevious</a:EnumNames>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>TXArea</a:Name>
                        <a:AliasName>tx_area</a:AliasName>
                        <a:DisplayName>摊销</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>TXAreaID</a:FieldName>
                                <a:AliasName>tx_area_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>PreviousCompletedArea</a:FieldName>
                                <a:DisplayName>前面完成建筑面积之和</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:DisplayName>顺序号</a:DisplayName>
                                <a:DataType>13</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>CompletedArea</a:FieldName>
                                <a:DisplayName>当月完成建筑面积</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>SaveQuantityAndJE</a:Name>
                        <a:AliasName>save_quantity_and_je</a:AliasName>
                        <a:DisplayName>保存购入数量和金额</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>SaveQuantityAndJEID</a:FieldName>
                                <a:AliasName>tx_area_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>zgID</a:FieldName>
                                <a:AliasName>zg_id</a:AliasName>
                                <a:DisplayName>自购周转材料表</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>GEQZGZZResource</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GRQuantity</a:FieldName>
                                <a:DisplayName>月购入数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GRJE</a:FieldName>
                                <a:DisplayName>月购入金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:DisplayName>顺序号</a:DisplayName>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQZLZZResource</a:Name>
                        <a:AliasName>wztj_geqzlzzresource</a:AliasName>
                        <a:DisplayName>租赁周转材料</a:DisplayName>
                        <a:Catalog>物资统计</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>GEQZLZZResourceID</a:FieldName>
                                <a:AliasName>wztj_geqzlzzresource_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>cbkmID</a:FieldName>
                                <a:AliasName>cbkm_id</a:AliasName>
                                <a:DisplayName>成本科目</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:MasterTableName>cbkm</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>TitleID</a:FieldName>
                                <a:AliasName>title_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:MasterTableName>GEQTitle</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>ResourceType</a:FieldName>
                                <a:AliasName>resource_type</a:AliasName>
                                <a:DisplayName>类别</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>2</a:DefaultExpr>
                                <a:EnumNames>1=人|2=材|3=机|4=专</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:AliasName>zlzz_code</a:AliasName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:AliasName>zlzz_name</a:AliasName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GGXH</a:FieldName>
                                <a:AliasName>zlzz_ggxh</a:AliasName>
                                <a:DisplayName>规格型号</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>DW</a:FieldName>
                                <a:AliasName>zlzz_dw</a:AliasName>
                                <a:DisplayName>单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>UnitDict</a:LookupTableName>
                                <a:LookupResult>Name</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>CostUnit</a:FieldName>
                                <a:AliasName>cost_unit</a:AliasName>
                                <a:DisplayName>计费单位</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>3</a:DefaultExpr>
                                <a:EnumNames>1=年|2=月|3=天|4=时</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Price</a:FieldName>
                                <a:AliasName>zlzz_price</a:AliasName>
                                <a:DisplayName>单价</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>EntryQuantity</a:FieldName>
                                <a:AliasName>entry_quantity</a:AliasName>
                                <a:DisplayName>进场数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>EntryDate</a:FieldName>
                                <a:AliasName>entry_date</a:AliasName>
                                <a:DisplayName>进场日期</a:DisplayName>
                                <a:DataType>9</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ReturnQuantity</a:FieldName>
                                <a:AliasName>return_quantity</a:AliasName>
                                <a:DisplayName>归还数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ReturnDate</a:FieldName>
                                <a:AliasName>return_date</a:AliasName>
                                <a:DisplayName>归还日期</a:DisplayName>
                                <a:DataType>9</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>RentDays</a:FieldName>
                                <a:AliasName>rent_days</a:AliasName>
                                <a:DisplayName>租用天数</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>IIF(IsNull(EntryDate) Or IsNull(ReturnDate),0,DaysBetween(EntryDate,ReturnDate)+1)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>RentOfThisMonth</a:FieldName>
                                <a:AliasName>rent_of_this_month</a:AliasName>
                                <a:DisplayName>本月租金</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(RentDays*EntryQuantity*Price,2)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ShortQuantity</a:FieldName>
                                <a:AliasName>short_quantity</a:AliasName>
                                <a:DisplayName>短少数量</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>PayForPrice</a:FieldName>
                                <a:AliasName>pay_for_price</a:AliasName>
                                <a:DisplayName>赔偿单价</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>PayForAmount</a:FieldName>
                                <a:AliasName>pay_for_amount</a:AliasName>
                                <a:DisplayName>赔偿金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(ShortQuantity*PayForPrice,2)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>AmountMoney</a:FieldName>
                                <a:AliasName>amount_money</a:AliasName>
                                <a:DisplayName>合计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>fRound(RentOfThisMonth+ShortQuantity*PayForPrice,2)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Supplier</a:FieldName>
                                <a:DisplayName>供应商名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>DataSource</a:FieldName>
                                <a:DisplayName>数据来源</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=FromNew|1=FromQuery|2=FromGEQ|3=FromPrevious</a:EnumNames>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQResourceType</a:Name>
                        <a:DisplayName>资源类别</a:DisplayName>
                        <a:Catalog>公共字典</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ResourceTypeID</a:FieldName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Description</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>1,&apos;人&apos;</o:Record>
                            <o:Record>2,&apos;材&apos;</o:Record>
                            <o:Record>3,&apos;机&apos;</o:Record>
                            <o:Record>4,&apos;专&apos;</o:Record>
                            <o:Record>5,&apos;他&apos;</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>FYLBDict</a:Name>
                        <a:DisplayName>费用类别字典</a:DisplayName>
                        <a:Catalog>费用汇总</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ID</a:FieldName>
                                <a:AliasName>fbtj_fylbdict_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Description</a:FieldName>
                                <a:DisplayName>代码名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>0,&apos;FBFX&apos;,&apos;FBFX&apos;,&apos;分部分项&apos;</o:Record>
                            <o:Record>1,&apos;FSTFY&apos;,&apos;FSTFY&apos;,&apos;非实体费用&apos;</o:Record>
                            <o:Record>2,&apos;QTZJF&apos;,&apos;QTZJF&apos;,&apos;其他直接费&apos;</o:Record>
                            <o:Record>3,&apos;JJF&apos;,&apos;JJF&apos;,&apos;间接费&apos;</o:Record>
                            <o:Record>4,&apos;JXF&apos;,&apos;JXF&apos;,&apos;机械费&apos;</o:Record>
                            <o:Record>5,&apos;SJGLF&apos;,&apos;SJGLF&apos;,&apos;上缴管理费&apos;</o:Record>
                            <o:Record>6,&apos;GF&apos;,&apos;GF&apos;,&apos;规费&apos;</o:Record>
                            <o:Record>7,&apos;SJ&apos;,&apos;SJ&apos;,&apos;税金&apos;</o:Record>
                            <o:Record>8,&apos;GCZJ&apos;,&apos;GCZJ&apos;,&apos;工程造价&apos;</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQSummary</a:Name>
                        <a:AliasName>wztj_geqsummary</a:AliasName>
                        <a:DisplayName>费用汇总</a:DisplayName>
                        <a:Catalog>费用汇总</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:KeyValueMacroFriendTableNames>GEQCostCode;cbkm</a:KeyValueMacroFriendTableNames>
                        <c:TableCustomMacroDefs>
                            <o:CustomMacroDef index="0">
                                <a:Code>FYXHJ</a:Code>
                                <a:Description>费用项合计</a:Description>
                                <a:ValueExpr>IIF(Option.EstiByPreTaxRate, GEQTitle.MarketPreTaxAmount[PID=NULL].SUM, GEQTitle.MarketTaxAmount[PID=NULL].SUM)</a:ValueExpr>
                                <a:ExtData>Catalog = 固定费用代码</a:ExtData>
                            </o:CustomMacroDef>
                            <o:CustomMacroDef index="0">
                                <a:Code>FYXHJZG</a:Code>
                                <a:Description>费用项合计-自购</a:Description>
                                <a:ValueExpr>GEQZGZZResource.TXJE[].SUM</a:ValueExpr>
                                <a:ExtData>Catalog = 固定费用代码</a:ExtData>
                            </o:CustomMacroDef>
                            <o:CustomMacroDef index="0">
                                <a:Code>FYXHJZL</a:Code>
                                <a:Description>费用项合计-租赁</a:Description>
                                <a:ValueExpr>GEQZLZZResource.AmountMoney[].SUM</a:ValueExpr>
                                <a:ExtData>Catalog = 固定费用代码</a:ExtData>
                            </o:CustomMacroDef>
                        </c:TableCustomMacroDefs>
                        <o:KeyValueMacroDef>
                            <a:CodeFieldName>FYDH</a:CodeFieldName>
                            <a:DescriptionFieldName>Name</a:DescriptionFieldName>
                            <a:ValueFieldName>JE</a:ValueFieldName>
                        </o:KeyValueMacroDef>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>GEQSummaryID</a:FieldName>
                                <a:AliasName>wztj_geqsummary_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SrcID</a:FieldName>
                                <a:AliasName>src_id</a:AliasName>
                                <a:DisplayName>在企业端的ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FLID</a:FieldName>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FLFullCode</a:FieldName>
                                <a:AliasName>flfull_code</a:AliasName>
                                <a:StrLength>1024</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>XH</a:FieldName>
                                <a:StrLength>1024</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FYDH</a:FieldName>
                                <a:DisplayName>费用代号</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                                <a:IsUnique>True</a:IsUnique>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="4">
                                <a:FieldName>QFJS</a:FieldName>
                                <a:DisplayName>取费基数</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FYSM</a:FieldName>
                                <a:DisplayName>费用说明</a:DisplayName>
                                <a:StrLength>2000</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FL</a:FieldName>
                                <a:DisplayName>费率</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>JE</a:FieldName>
                                <a:DisplayName>金额</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>IIF(IsNull(FL), QFJS, QFJS * FL / 100)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>cbkmID</a:FieldName>
                                <a:AliasName>cbkm_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>cbkm</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                                <a:MasterTableName>cbkm</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>FYLBID</a:FieldName>
                                <a:DisplayName>费用类别</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>FYLBDict</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>Description</a:LookupDescription>
                                <a:MasterTableName>FYLBDict</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:StrLength>2000</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>ModifyKey</a:FieldName>
                                <a:AliasName>modify_key</a:AliasName>
                                <a:DisplayName>修改状态</a:DisplayName>
                                <a:Hint>唯一标识</a:Hint>
                                <a:DataType>10</a:DataType>
                                <a:CalcExpr>XH+&apos;_&apos;+FYDH+&apos;_&apos;+Name+&apos;_&apos;+FloatToStr(QFJS)+&apos;_&apos;+FloatToStr(FL)+&apos;_&apos;+FloatToStr(FYLBID)+&apos;_&apos;+FloatToStr(cbkmID)+&apos;_&apos;+Remark</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TJ_JE</a:FieldName>
                                <a:DisplayName>编制总金额</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TJ_PeriodData</a:FieldName>
                                <a:AliasName>tj_period_data</a:AliasName>
                                <a:DisplayName>每期数据</a:DisplayName>
                                <a:DataType>10</a:DataType>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TJ_CalcPeriodData</a:FieldName>
                                <a:AliasName>tj_calc_period_data</a:AliasName>
                                <a:DisplayName>计算每期数据</a:DisplayName>
                                <a:DataType>10</a:DataType>
                                <a:CalcExpr>IIF(Option.Index = NULL, &apos;&apos;, GBQ42dKeyValueArray( IntToStr(Option.Index), &apos;JE&apos;, JE, &apos;TJ_PeriodData&apos; ))</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>StageDivisionID</a:FieldName>
                                <a:AliasName>stage_division_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                                <a:LookupKind>1</a:LookupKind>
                                <a:LookupTableName>StageDivision</a:LookupTableName>
                                <a:LookupResult>ID</a:LookupResult>
                                <a:LookupDescription>Name</a:LookupDescription>
                                <a:DefaultExpr>1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>StageDivisionNotOnlyIsOverall</a:FieldName>
                                <a:AliasName>stage_division_not_only_is_overall</a:AliasName>
                                <a:DataType>1</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>Amount</a:FieldName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>IIF(~FYLBID.Code = &apos;GCZJ&apos;, JE, 0)</a:CalcExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>GEQCostCode</a:Name>
                        <a:AliasName>wztj_geqcost_code</a:AliasName>
                        <a:DisplayName>费用编码</a:DisplayName>
                        <a:Catalog>费用编码</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <o:KeyValueMacroDef>
                            <a:CodeFieldName>CostCode</a:CodeFieldName>
                            <a:DescriptionFieldName>Description</a:DescriptionFieldName>
                            <a:ValueFieldName>Amount</a:ValueFieldName>
                        </o:KeyValueMacroDef>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>GEQCostCodeID</a:FieldName>
                                <a:AliasName>wztj_geqcost_code_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>CostCode</a:FieldName>
                                <a:AliasName>cost_code</a:AliasName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:IsUnique>True</a:IsUnique>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Description</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Amount</a:FieldName>
                                <a:DisplayName>金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>cbkm</a:Name>
                        <a:AliasName>wztj_cbkm</a:AliasName>
                        <a:DisplayName>成本科目字典</a:DisplayName>
                        <a:Catalog>成本科目</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <o:KeyValueMacroDef>
                            <a:CodeFieldName>FYDM</a:CodeFieldName>
                            <a:DescriptionFieldName>Name</a:DescriptionFieldName>
                            <a:ValueFieldName>SummaryAmount</a:ValueFieldName>
                        </o:KeyValueMacroDef>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ID</a:FieldName>
                                <a:AliasName>wztj_cbkm_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>PID</a:FieldName>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>cbkm</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>IsLeaf</a:FieldName>
                                <a:AliasName>is_leaf</a:AliasName>
                                <a:DataType>1</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Level</a:FieldName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FullCode</a:FieldName>
                                <a:AliasName>full_code</a:AliasName>
                                <a:StrLength>1024</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:AliasName>order_no</a:AliasName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>DeptId</a:FieldName>
                                <a:AliasName>dept_id</a:AliasName>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FullDeptId</a:FieldName>
                                <a:AliasName>full_dept_id</a:AliasName>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>JianPin</a:FieldName>
                                <a:AliasName>jian_pin</a:AliasName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>PinYin</a:FieldName>
                                <a:AliasName>pin_yin</a:AliasName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>CBKMZDBM</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SX</a:FieldName>
                                <a:StrLength>50</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SYXS</a:FieldName>
                                <a:DataType>1</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>LastUpdateTime</a:FieldName>
                                <a:AliasName>last_update_time</a:AliasName>
                                <a:StrLength>128</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>IsDelete</a:FieldName>
                                <a:AliasName>is_delete</a:AliasName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>DW</a:FieldName>
                                <a:DisplayName>科目单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SrcID</a:FieldName>
                                <a:AliasName>src_id</a:AliasName>
                                <a:DisplayName>在企业端表中的ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>GEQAmount</a:FieldName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>GEQResource.Amount[cbkmID=?ID].Sum</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FYDM</a:FieldName>
                                <a:DisplayName>费用代码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:IsUnique>True</a:IsUnique>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:StrLength>2000</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="6">
                                <a:FieldName>SXZD</a:FieldName>
                                <a:StrLength>255</a:StrLength>
                                <a:NullExpr>IIF(SX=&apos;CL&apos;, &apos;自施材料&apos;, 
IIF(SX=&apos;ZYFB&apos;, &apos;专业分包&apos;,
IIF(SX=&apos;LWFB&apos;, &apos;劳务分包&apos;,
IIF(SX=&apos;JXSB&apos;, &apos;机械设备&apos;,
IIF(SX=&apos;FSTFY&apos;, &apos;非实体费用&apos;,
IIF(SX=&apos;AQWS&apos;, &apos;安全文施（摊销入成本）&apos;,
IIF(SX=&apos;LSSS&apos;, &apos;临时设施（摊销入成本）&apos;, &apos;&apos;
)
)
)
)
)
)
)</a:NullExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>LevelNum</a:FieldName>
                                <a:AliasName>level_num</a:AliasName>
                                <a:DataType>2</a:DataType>
                                <a:CalcExpr>iif(PID = NULL, 1, ~PID.LevelNum+1)</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>SummaryAmount</a:FieldName>
                                <a:AliasName>summary_amount</a:AliasName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>GEQResource.Amount[cbkmID=?ID].Sum + GEQZGZZResource.TXJE[cbkmID=?ID].Sum + GEQZLZZResource.AmountMoney[cbkmID=?ID].Sum + SummaryAmount[PID=?ID].sum</a:CalcExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="1">
                    <o:TableSchema>
                        <a:Name>Option</a:Name>
                        <a:AliasName>wztj_option</a:AliasName>
                        <a:DisplayName>工程选项</a:DisplayName>
                        <a:Catalog>选项设置</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Version</a:FieldName>
                                <a:DisplayName>版本号</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Region</a:FieldName>
                                <a:DisplayName>地区</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TaxMethod</a:FieldName>
                                <a:AliasName>tax_method</a:AliasName>
                                <a:DisplayName>计税方式</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>2</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>EstiByPreTaxRate</a:FieldName>
                                <a:AliasName>esti_by_pre_tax_rate</a:AliasName>
                                <a:DisplayName>不含税市场价组价</a:DisplayName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>False</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>GEQFileType</a:FieldName>
                                <a:AliasName>geqfile_type</a:AliasName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=成本编制|1=变更|2=统计</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>GBQFile</a:FieldName>
                                <a:DisplayName>导入的GBQ文件名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BGColor</a:FieldName>
                                <a:DisplayName>颜色设置</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FilterBillVisible</a:FieldName>
                                <a:AliasName>filter_bill_visible</a:AliasName>
                                <a:DisplayName>过滤显示</a:DisplayName>
                                <a:DataType>1</a:DataType>
                                <a:DefaultExpr>false</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FBFXFilterCondition</a:FieldName>
                                <a:AliasName>fbfxfilter_condition</a:AliasName>
                                <a:DisplayName>分部分项过滤条件</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FilterResourceCondition</a:FieldName>
                                <a:AliasName>filter_resource_condition</a:AliasName>
                                <a:DisplayName>人材机过滤条件</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FilterNonEntityCost</a:FieldName>
                                <a:AliasName>filter_non_entity_cost</a:AliasName>
                                <a:DisplayName>非实体过滤条件</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ColorVersion</a:FieldName>
                                <a:AliasName>color_version</a:AliasName>
                                <a:DisplayName>颜色版本</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>-1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Index</a:FieldName>
                                <a:DisplayName>期数</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Year</a:FieldName>
                                <a:DisplayName>年份</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Month</a:FieldName>
                                <a:DisplayName>月份</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ID</a:FieldName>
                                <a:AliasName>wztj_option_id</a:AliasName>
                                <a:DisplayName>单据ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TJAmount</a:FieldName>
                                <a:DisplayName>本期统计金额</a:DisplayName>
                                <a:DataType>8</a:DataType>
                                <a:Precision>2</a:Precision>
                                <a:CalcExpr>GEQSummary.JE[~FYLBID.Code=&apos;GCZJ&apos;].SUM</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>HTName</a:FieldName>
                                <a:DisplayName>合同名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>YSAmount</a:FieldName>
                                <a:DisplayName>预算金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>TotalAmount</a:FieldName>
                                <a:AliasName>total_amount</a:AliasName>
                                <a:DisplayName>累计统计金额</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:CalcExpr>PrevTotalAmount+TJAmount</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>FBDW</a:FieldName>
                                <a:DisplayName>分包单位</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>FBLX</a:FieldName>
                                <a:DisplayName>分包类型</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=劳务分包|1=专业分包</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>EditPerson</a:FieldName>
                                <a:AliasName>edit_person</a:AliasName>
                                <a:DisplayName>编制人</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>EditDateTime</a:FieldName>
                                <a:AliasName>edit_date_time</a:AliasName>
                                <a:DisplayName>编制日期</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:DisplayName>备注</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>CheckStatus</a:FieldName>
                                <a:AliasName>check_status</a:AliasName>
                                <a:DisplayName>审核状态</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=未审核|1=已审核</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>PrevTotalAmount</a:FieldName>
                                <a:AliasName>prev_total_amount</a:AliasName>
                                <a:DataType>7</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>WZType</a:FieldName>
                                <a:DisplayName>物资类型</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:EnumNames>0=混凝土|1=钢筋|2=库存材料|3=周转材料</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BuildingArea</a:FieldName>
                                <a:AliasName>building_area</a:AliasName>
                                <a:DisplayName>建筑面积</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>&apos;&apos;,&apos;&apos;,2,0,0,&apos;&apos;,&apos;&apos;,0,&apos;&apos;,&apos;&apos;,&apos;&apos;,,,,,,0.00,&apos;&apos;,0,0,&apos;&apos;,0,&apos;&apos;,&apos;&apos;,&apos;&apos;,0,,0,</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="1">
                    <o:TableSchema>
                        <a:Name>PrecisionOption</a:Name>
                        <a:DisplayName>工程精度</a:DisplayName>
                        <a:Catalog>选项设置</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>ResRate</a:FieldName>
                                <a:AliasName>res_rate</a:AliasName>
                                <a:DisplayName>材料单价精度</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>NormQuantity</a:FieldName>
                                <a:AliasName>norm_quantity</a:AliasName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BQQuantity</a:FieldName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>NormRate</a:FieldName>
                                <a:AliasName>norm_rate</a:AliasName>
                                <a:DisplayName>子目单价精度</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>NormAmount</a:FieldName>
                                <a:AliasName>norm_amount</a:AliasName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BQRate</a:FieldName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>BQAmount</a:FieldName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>TitleAmount</a:FieldName>
                                <a:AliasName>title_amount</a:AliasName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Usage</a:FieldName>
                                <a:DisplayName>含量精度</a:DisplayName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>2,6,6,2,2,2,2,2,6</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>StageDivision</a:Name>
                        <a:AliasName>wztj_stage_division</a:AliasName>
                        <a:DisplayName>阶段划分</a:DisplayName>
                        <a:Catalog>公共字典</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>ID</a:FieldName>
                                <a:AliasName>raw_id</a:AliasName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>阶段名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:AliasName>order_no</a:AliasName>
                                <a:DataType>2</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SrcID</a:FieldName>
                                <a:AliasName>src_id</a:AliasName>
                                <a:DisplayName>企业端ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>XTNZ</a:FieldName>
                                <a:DataType>1</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>YunID</a:FieldName>
                                <a:AliasName>wztj_stage_division_id</a:AliasName>
                                <a:DisplayName>云端的ID</a:DisplayName>
                                <a:DataType>15</a:DataType>
                                <a:IsUnique>True</a:IsUnique>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData>
                        <c:Records Format="1">
                            <o:Record>1,&apos;整体&apos;,0,,&apos;&apos;,1,1</o:Record>
                        </c:Records>
                    </o:TableData>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>SegmentGroup</a:Name>
                        <a:AliasName>wztj_segment_group</a:AliasName>
                        <a:DisplayName>流水段分组</a:DisplayName>
                        <a:Catalog>流水视图</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>SegmentGroupID</a:FieldName>
                                <a:AliasName>wztj_segment_group_id</a:AliasName>
                                <a:DisplayName>流水段分组ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>PID</a:FieldName>
                                <a:DisplayName>流水段分组父ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>Segmentgroup</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SpecialtyID</a:FieldName>
                                <a:AliasName>specialty_id</a:AliasName>
                                <a:DisplayName>专业</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Remark</a:FieldName>
                                <a:DisplayName>备注</a:DisplayName>
                                <a:DataType>10</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>GroupType</a:FieldName>
                                <a:AliasName>group_type</a:AliasName>
                                <a:DisplayName>分组类型</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>1</a:DefaultExpr>
                                <a:EnumNames>1=byfloor|2=custom</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SortElevation</a:FieldName>
                                <a:AliasName>sort_elevation</a:AliasName>
                                <a:DisplayName>级别排序</a:DisplayName>
                                <a:DataType>7</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:IsIndexField>True</a:IsIndexField>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>Type</a:FieldName>
                                <a:DisplayName>类型</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:EnumNames>1=Building|2=Specialty|3=Floor|4=None</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>Status</a:FieldName>
                                <a:DisplayName>状态</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>2</a:DefaultExpr>
                                <a:EnumNames>1=Delay|2=Normal|3=Inadvance</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:AliasName>order_no</a:AliasName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OriginPrimaryID</a:FieldName>
                                <a:AliasName>origin_primary_id</a:AliasName>
                                <a:DisplayName>源主键ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>IsAlone</a:FieldName>
                                <a:AliasName>is_alone</a:AliasName>
                                <a:DataType>2</a:DataType>
                                <a:CalcExpr>IsAlone[PID = ?SegmentGroupID].SUM + Segment.IsAlone[SegmentGroupID = ?SegmentGroupID].SUM</a:CalcExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>ResID</a:FieldName>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>GEQResource</a:MasterTableName>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>Segment</a:Name>
                        <a:AliasName>wztj_segment</a:AliasName>
                        <a:DisplayName>流水段</a:DisplayName>
                        <a:Catalog>流水视图</a:Catalog>
                        <a:CommandEnable>True</a:CommandEnable>
                        <a:PrimaryKeyMinVal>1</a:PrimaryKeyMinVal>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>SegmentID</a:FieldName>
                                <a:AliasName>wztj_segment_id</a:AliasName>
                                <a:DisplayName>流水段ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="1">
                                <a:FieldName>SegmentGroupID</a:FieldName>
                                <a:AliasName>segment_group_id</a:AliasName>
                                <a:DisplayName>流水段分组ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>15</a:DataType>
                                <a:Aggregate>True</a:Aggregate>
                                <a:MasterTableName>SegmentGroup</a:MasterTableName>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>SpecialtyID</a:FieldName>
                                <a:AliasName>specialty_id</a:AliasName>
                                <a:DisplayName>专业</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Code</a:FieldName>
                                <a:DisplayName>编码</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Name</a:FieldName>
                                <a:DisplayName>名称</a:DisplayName>
                                <a:StrLength>255</a:StrLength>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>CustomType</a:FieldName>
                                <a:AliasName>custom_type</a:AliasName>
                                <a:DisplayName>自定义类型</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>1</a:DefaultExpr>
                                <a:EnumNames>1=按楼层构件类型|2=按系统</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="3">
                                <a:FieldName>Status</a:FieldName>
                                <a:DisplayName>任务状态</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>0</a:DefaultExpr>
                                <a:EnumNames>0=未开始|1=进行中|2=已完成|3=未定义1|4=未定义2|5=未定义3|6=未定义4</a:EnumNames>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OrderNo</a:FieldName>
                                <a:AliasName>order_no</a:AliasName>
                                <a:DisplayName>顺序号</a:DisplayName>
                                <a:DataType>2</a:DataType>
                                <a:DefaultExpr>1</a:DefaultExpr>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Relate</a:FieldName>
                                <a:DisplayName>关联</a:DisplayName>
                                <a:DataType>1</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>OriginPrimaryID</a:FieldName>
                                <a:AliasName>origin_primary_id</a:AliasName>
                                <a:DisplayName>源主键ID</a:DisplayName>
                                <c:ExtPropDefs>
                                    <o:ExtPropDef>
                                        <a:Code>DataType</a:Code>
                                        <a:Value>Int64</a:Value>
                                    </o:ExtPropDef>
                                </c:ExtPropDefs>
                                <a:DataType>13</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="5">
                                <a:FieldName>IsAlone</a:FieldName>
                                <a:AliasName>is_alone</a:AliasName>
                                <a:DataType>2</a:DataType>
                                <a:CalcExpr>IIF(BIMFHL.BIMFHLID[SegmentID = ?SegmentID].Exist, 1, 0)</a:CalcExpr>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
            </c:Tables>
        </o:Database>
        <o:Database>
            <a:Name>DesktopDB</a:Name>
            <c:Tables>
                <o:Table TableKind="0">
                    <o:TableSchema>
                        <a:Name>Desktop</a:Name>
                        <a:ApplyUpdateEnabled>False</a:ApplyUpdateEnabled>
                        <c:FieldSchemas>
                            <o:FieldSchema FieldKind="0">
                                <a:FieldName>DesktopID</a:FieldName>
                                <a:DataType>15</a:DataType>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Section</a:FieldName>
                                <a:DataType>10</a:DataType>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Ident</a:FieldName>
                                <a:DataType>10</a:DataType>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                            <o:FieldSchema FieldKind="2">
                                <a:FieldName>Value</a:FieldName>
                                <a:DataType>10</a:DataType>
                                <a:TrimSpace>False</a:TrimSpace>
                            </o:FieldSchema>
                        </c:FieldSchemas>
                    </o:TableSchema>
                    <o:TableData/>
                </o:Table>
            </c:Tables>
        </o:Database>
    </c:Databases>
</GSPModel>
