@echo off

set /p ruta=Escribe la ruta donde se crea la carpeta:
set /p cod_pro=Codigo de Proyecto:

set parent_d=%ruta%NV_%cod_pro%_%date:~0,2%%date:~3,2%%date:~6,4%

mkdir %parent_d%


mkdir %parent_d%\Ejecutables
mkdir %parent_d%\Scripts
mkdir %parent_d%\Documentos
mkdir %parent_d%\Rollback

pause