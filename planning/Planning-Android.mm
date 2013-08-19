<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1341105845912" ID="ID_1938410307" MODIFIED="1341105859897" TEXT="Fin para Android">
<node CREATED="1341105862873" ID="ID_1718168081" MODIFIED="1341105868244" POSITION="right" TEXT="Objetivos">
<node CREATED="1341105935949" ID="ID_1910945079" MODIFIED="1341107505716" TEXT="Abstrair o modelo para a web, no desktop e android">
<node CREATED="1341106931777" ID="ID_521718658" MODIFIED="1341107137213" TEXT="Extrair as propriedades das classes do modelo para interfaces"/>
<node CREATED="1341107139294" ID="ID_579028382" MODIFIED="1341107155204" TEXT="Extrair uma interface de DAO gen&#xe9;rica"/>
<node CREATED="1341107314994" ID="ID_471641641" MODIFIED="1341107351185" TEXT="Extrair os m&#xe9;todos especializados dos DAOs para interfaces"/>
<node CREATED="1341106819880" ID="ID_209779072" MODIFIED="1341108402918" TEXT="Criar um ModelFactory para ficar respons&#xe1;vel pela cria&#xe7;&#xe3;o dos objetos do modelo e DAOs">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Essa classe na verdade ser&#225; abstrata. Dever&#225; ter uma subclasse para JPA e outra para o Android. Definir depois quem ir&#225; decidir pela cria&#231;&#227;o de qual subclasse
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1341107393136" ID="ID_237207442" MODIFIED="1341107411108" TEXT="Adaptar classes de neg&#xf3;cio para usar o ModelFactory"/>
<node CREATED="1341108811289" ID="ID_557675806" MODIFIED="1341108813500" TEXT="Tarefas">
<node CREATED="1341108840895" ID="ID_1623070716" MODIFIED="1341108907452" TEXT="Provar conceito">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Fazer no objeto de conta, fazer o controlefinanceiro usar ele e o fin
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1341108495046" ID="ID_8158340" MODIFIED="1341109047983" TEXT="Inser&#xe7;&#xe3;o de objeto no banco (conta-add)"/>
<node CREATED="1341108579520" ID="ID_1112615323" MODIFIED="1341109162926" TEXT="Listagem com condi&#xe7;&#xe3;o (pr&#xe9;-requisito para o pr&#xf3;ximo)"/>
<node CREATED="1341108506694" ID="ID_1094908475" MODIFIED="1341108969741" TEXT="Exclus&#xe3;o de objeto no banco (conta-rm)"/>
<node CREATED="1341108430091" ID="ID_1869241947" MODIFIED="1341109031567" TEXT="Listagem simples (conta-ls)"/>
<node CREATED="1341108522700" ID="ID_1769546754" MODIFIED="1341109299603" TEXT="Atualiza&#xe7;&#xe3;o de objeto (opcional, s&#xf3; o extrato analyse que vai utilizar)"/>
</node>
<node CREATED="1341108914060" ID="ID_1255575717" MODIFIED="1341108920812" TEXT="Fazer para os demais objetos do modelo"/>
</node>
</node>
<node CREATED="1341105872030" ID="ID_1390617381" MODIFIED="1341109687730" TEXT="Abstrair o controller para o desktop, web e android">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Verificar se o controller pode ser usado no Android sem problema
    </p>
    <p>
      Fazer controller receber uma interface environment que ter&#225; tr&#234;s implementa&#231;&#245;es: command line, web e android. O controller dever&#225; instanciar uma subclasse de ModelFactory que ser&#225; devolvida para as classes de regra de neg&#243;cio.
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1341109374439" ID="ID_1168000042" MODIFIED="1341109747774" TEXT="Tirar os system.out da regra de neg&#xf3;cio">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Fazer eles devolverem um objeto de volta pra o controller
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1341105872030" ID="ID_875886222" MODIFIED="1341107613765" TEXT="Abstrair o view para o desktop, web e android">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Ver projeto do observer, acho que ainda vai ter que abstrair um controller melhor
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
</map>
