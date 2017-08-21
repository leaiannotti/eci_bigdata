blocks = LOAD '/home/leoian/Escritorio/BigData/pig/bigdataexample.csv' USING PigStorage(',')
as
(lote:chararray,loc:chararray,id:chararray,storer:chararray,sku:chararray,qty:float,status:chararray,adddate:chararray,whoadd:chararray);

STORE blocks INTO '/home/leoian/Escritorio/BigData/pig/res1.csv';

A = FILTER blocks BY status == 'OK';
STORE A INTO '/home/leoian/Escritorio/BigData/pig/res2.csv';

B = FILTER A BY whoadd == 'e_cretamal';
STORE B INTO '/home/leoian/Escritorio/BigData/pig/res3.csv';

C = GROUP B BY storer;
STORE C INTO '/home/leoian/Escritorio/BigData/pig/res4.csv';

D = FOREACH C GENERATE group, SUM(B.qty);
STORE D INTO '/home/leoian/Escritorio/BigData/pig/res5.csv';