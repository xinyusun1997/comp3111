# COMP3111: Software Engineering Project - Webscrapper
## * Group name :  Hasaki

|  Member | Task | Task | | Student ID<br />
> Li Haoran | Basic 4 | Adv3 | 20413540<br />
> WANG Haoqi | Basic 1 | Adv1 | 20412986<br />
> SUN Xinyu | Basic6 | Adv2 | 20327715<br />

* Assumption
1. Close Function will reset all, include Last Search Function (Disabled Last Search)
2. The reselling portal of the items are "https://www.google.com", "https://www.amazon.com","https://www.netflix.com", "https://www.facebook.com", "https://www.apple.com", "https://www.bbc.com", "https://www.imdb.com", "https://www.twitter.com"

## Recalculation of coverage test

Controller
Element	Missed Instructions	Cov.	Missed Branches	Cov.	Missed	Cxty	Missed	Lines	Missed	Methods<br />

UI combined, exclude -> trendFill(List, String)	686	0%	64	0%	33	33	103	103	1	1<br />
UI combined, exclude -> actionSearch()	88	0%	4	0%	3	3	19	19	1	1<br />
UI combined, exclude -> tableFill(List)	79	0%	8	0%	5	5	17	17	1	1<br />
UI combined, exclude -> LoadResult()	72	0%	2	0%	2	2	20	20	1	1<br />
UI combined, exclude -> lambda$trendFill$3(String[], int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	72	0%	18	0%	10	10	12	12	1	1<br />
UI combined, exclude -> lambda$trendFill$9(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> lambda$trendFill$8(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> lambda$trendFill$7(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> lambda$trendFill$6(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> lambda$trendFill$5(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> lambda$trendFill$4(String[], XYChart.Data, int[], XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, XYChart.Data, MouseEvent)	68	0%	16	0%	9	9	11	11	1	1<br />
UI combined, exclude -> closeTabs()	64	0%	2	0%	2	2	15	15	1	1<br />
UI combined, exclude -> SaveResult()	59	0%	6	0%	4	4	16	16	1	1<br />
UI combined, exclude -> actionNew()	57	0%	2	0%	2	2	14	14	1	1<br />
UI combined, exclude -> Advance1(List, String)	52	0%	4	0%	3	3	8	8	1	1<br />
UI combined, exclude -> lambda$trendFill$10(ActionEvent)	50	0%	6	0%	4	4	10	10	1	1<br />
UI combined, exclude -> Basic1(List)	41	0%		n/a	1	1	8	8	1	1<br />
UI combined, exclude -> lambda$Advance1$2(XYChart.Data, List, XYChart.Series, MouseEvent)	40	0%	8	0%	5	5	8	8	1	1<br />
UI combined, exclude -> showTeamInformation()	21	0%		n/a	1	1	6	6	1	1<br />
summeryClickMin(List)	724	77%	13	75%	1	3	4	10	0	1<br />
summeryClickLatest(List)	724	77%	13	75%	1	3	4	10	0	1<br />
UI combined, exclude -> QuitFunction()	6	0%		n/a	1	1	2	2	1	1<br />
UI combined, exclude -> initialize()		0%		n/a	1	1	2	2	1	1<br />
UI combined, exclude -> lambda$Basic1$1(List, ActionEvent)		0%		n/a	1	1	2	2	1	1<br />
UI combined, exclude -> lambda$Basic1$0(List, ActionEvent)		0%		n/a	1	1	2	2	1	1<br />
LogicLoad(File)	145	97%	14	100%	0	8	2	39	0	1<br />
LogicSave(File, String, List)	70	95%	4	100%	0	3	2	22	0	1<br />
trendFillLogicHelper(List, double[], Date[], String[], int[])	65	95%	6	100%	0	4	2	13	0	1<br />
LogicSearchName(File)	40	93%	4	100%	0	3	2	14	0	1<br />
barChartFill(List, String)	347	100%	10	100%	0	6	0	32	0	1<br />
trendFillLogic(List, String)	312	100%	32	100%	0	17	0	55	0	1<br />
summeryFill(List)	252	100%	38	100%	0	20	0	51	0	1<br />
barChartClick(List, XYChart.Data)	136	100%	18	100%	0	10	0	22	0	1<br />
tableFillLogic(List)	36	100%	6	100%	0	4	0	9	0	1<br />
LogicShowResult(List)	24	100%	2	100%	0	2	0	5	0	1<br />
Controller()	22	100%		n/a	0	1	0	7	0	1<br />
InformationString()	16	100%		n/a	0	1	0	6	0	1<br />
<br />
Total	branch 3+3+8+3+4+3+6+17+20+10+4+2+1 = 84 <br />
missed	branch 1+1=2 <br />
Total branch convergence is 1-2/84=0.976 <br />
Total instructions 10+10+39+22+13+14+32+55+51+22+9+5 = 282 <br />
Total missed instructions 4+4+2+2+2+2 = 16 <br />
Total instructions convergence 1-16/282 = 0.943 <br />
