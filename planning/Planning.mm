<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1322269798993" ID="ID_1526915694" MODIFIED="1324252493269" TEXT="jfin - Planning">
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269815461" FOLDED="true" ID="ID_1671579002" MODIFIED="1333071240580" POSITION="right" TEXT="Milestone 1">
<node CREATED="1322269881657" ID="ID_802172816" MODIFIED="1322269949887" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Permitir o lan&#231;amento de informa&#231;&#245;es realizadas no sistema, isto &#233;, um n&#237;vel m&#237;nimo de funcionalidade.
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269835544" ID="ID_858403788" MODIFIED="1322271235590" TEXT="jfin conta ls"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269843202" ID="ID_82993463" MODIFIED="1322271238731" TEXT="jfin conta add"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269851556" ID="ID_54194112" MODIFIED="1322271240845" TEXT="jfin lancamento ls"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269855719" ID="ID_587171869" MODIFIED="1322271242662" TEXT="jfin lancamento add"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269869576" ID="ID_1120656294" MODIFIED="1322271244541" TEXT="jfin lancamento addfull"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269861373" ID="ID_1338423851" MODIFIED="1322271246729" TEXT="jfin lancamento balance"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270227071" ID="ID_704897316" MODIFIED="1322306803378" TEXT="jfin lancamento rm"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322271367843" ID="ID_901785769" MODIFIED="1322314163539" TEXT="jfin conta rm"/>
</node>
<node CREATED="1322269963729" ID="ID_678990671" MODIFIED="1322269970265" POSITION="left" TEXT="Legenda">
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269973722" ID="ID_1540285104" MODIFIED="1322269993883" TEXT="Ok (F3)"/>
<node CREATED="1322269975090" ID="ID_716897189" MODIFIED="1322270304082" TEXT="Pendente (F1)"/>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270133624" FOLDED="true" ID="ID_209304459" MODIFIED="1333071239090" POSITION="right" TEXT="Milestone 2">
<node CREATED="1322270137269" ID="ID_1707435879" MODIFIED="1322270160278" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Simula&#231;&#227;o com base nos modelos
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270006667" ID="ID_91797734" MODIFIED="1322324228369" TEXT="jfin modelo ls"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270015940" ID="ID_86476663" MODIFIED="1322324755325" TEXT="jfin modelo add"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270266890" ID="ID_451381050" MODIFIED="1322329286293" TEXT="jfin lancmodelo ls"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270272594" ID="ID_1336424038" MODIFIED="1322348876064" TEXT="jfin lancmodelo add"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270360502" ID="ID_1702994864" MODIFIED="1322353270970" TEXT="jfin modelo clone"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270364632" ID="ID_607677520" MODIFIED="1322616035179" TEXT="jfin modelo simulate">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Dever&#225; mostrar os saldos, assim como no lanc ls
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270400623" FOLDED="true" ID="ID_674416975" MODIFIED="1333071237858" POSITION="right" TEXT="Milestone 3">
<node CREATED="1322270408325" ID="ID_662000060" MODIFIED="1322270930606" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Importa&#231;&#227;o de extrato banc&#225;rio
    </p>
    <p>
      - An&#225;lise do previsto
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270492830" ID="ID_1372398862" MODIFIED="1322680648248" TEXT="Altera&#xe7;&#xf5;es no banco de dados">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      nova tabela:
    </p>
    <p>
      
    </p>
    <p>
      - extrato
    </p>
    <p>
      &#160;&#160;- id
    </p>
    <p>
      &#160;&#160;- bancoid (fk to conta.id)
    </p>
    <p>
      &#160;&#160;- data
    </p>
    <p>
      &#160;&#160;- original
    </p>
    <p>
      
    </p>
    <p>
      alter table conta add field extrato --(&#233; como a conta dever&#225; ser localizada em uma linha do extrato)
    </p>
    <p>
      alter table lancamento add field extratoid fk to extrato.id
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270952879" ID="ID_170195965" MODIFIED="1323017675984" TEXT="jfin modelo analyse">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Par&#226;metros: &lt;nome modelo&gt; &lt;data base&gt; &lt;intervalo de lan&#231;amentos para verificar - opcional, default: database-database+1 m&#234;s&gt;
    </p>
    <p>
      - Abre os lancamentos dentro da faixa de datas especificadas e os compara com os lan&#231;amentos do modelo
    </p>
    <p>
      - Caso um lan&#231;amento do modelo tenha mesma origem e destino e o lan&#231;amento realizado tenha lanctomodeloid null, atualizar o lanctomodeloid para o id do lancamento do modelo.
    </p>
    <p>
      - Caso exista mais de um lan&#231;amento com mesma origem e destino (e lanctomodeloid nulo), n&#227;o fazer o lan&#231;amento, informar a condi&#231;&#227;o ao usu&#225;rio
    </p>
    <p>
      - Apenas mostrar o que ser&#225; feito. Receber da linha de comando uma flag --realizar para gravar no banco de dados
    </p>
    <p>
      - Criar um switch boolean para apenas diferenciar entre uma simula&#231;&#227;o e realiza&#231;&#227;o do modelo no banco de dados.
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322778310655" ID="ID_532456165" MODIFIED="1322781904886" TEXT="jfin extrato ls">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Filtro por conta, data inicio e fim
    </p>
    <p>
      - Apenas listas o extrato
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270725708" FOLDED="true" ID="ID_623657749" MODIFIED="1326277296129" TEXT="jfin extrato import ">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      fin extrato import &quot;conta destino&quot; &quot;arquivo de extrato.csv&quot;
    </p>
    <p>
      
    </p>
    <p>
      Somente lan&#231;a os dados do extrato na tabela de extrato criada na &#250;ltimas altera&#231;&#245;es do banco (n&#227;o mexe com lancamento neste momento)
    </p>
    <p>
      
    </p>
    <p>
      - Depois que isso estiver pronto, pegar no extrato do cart&#227;o de cr&#233;dito as despesas que est&#227;o caindo todo o m&#234;s
    </p>
  </body>
</html></richcontent>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323182444054" ID="ID_1629006429" MODIFIED="1323183856760" TEXT="Altera&#xe7;&#xf5;es no Banco de Dados">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ArquivoExtrato
    </p>
    <p>
      - id: int (PK)
    </p>
    <p>
      - arquivo: text (lan&#231;ar o arquivo original aqui)
    </p>
    <p>
      
    </p>
    <p>
      Adicionar coluna ArquivoExtratoID ao Extrato (FK -&gt; ArquivoExtrato)
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323182306524" ID="ID_1007960337" MODIFIED="1323185084704" TEXT="Altera&#xe7;&#xe3;o no Modelo">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Cria&#231;&#227;o das novas entidades de ArquivoExtrato
    </p>
    <p>
      - ArquivoExtratoDAO
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323182420467" ID="ID_1672761856" MODIFIED="1323185948318" TEXT="Atualiza&#xe7;&#xe3;o da importa&#xe7;&#xe3;o de extrato"/>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323572186166" ID="ID_1588496469" MODIFIED="1323629693986" TEXT="jfin ref add">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Recebe: conta, banco e regex
    </p>
    <p>
      
    </p>
    <p>
      Adiciona a linha em controlefinanceiro_extrato.referenciaextrato
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1322271284140" ID="ID_1921731818" MODIFIED="1324236134797" TEXT="jfin extrato analyse">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Recebe como par&#226;metro uma data de in&#237;cio e fim (padr&#227;o da data fim: hoje)
    </p>
    <p>
      - Tem como finalidade lan&#231;ar dados previamente importados da tabela de extrato para a tabela de lancamentos
    </p>
    <p>
      
    </p>
    <p>
      - Relacionar as linhas do extrato dentro das datas informadas com os lancamento realizados (tamb&#233;m dentro da mesma data, talvez receber dois pares de datas)
    </p>
    <p>
      - Dever&#225; gerar um update do lancamentomodeloid caso consiga relacionar a linha do extrato com algum lancamento (nesse caso os dois deverao ter a mesma data e valor), confirmar a opera&#231;&#227;o com o usu&#225;rio
    </p>
    <p>
      - Caso n&#227;o seja poss&#237;vel relacionar, dever&#225; ser gerado um insert. Consultar o novo campo extrato da conta para saber que conta dever&#225; ser utilizada para isso.
    </p>
    <p>
      - Fazer isso em uma classe java que devera receber o nome do banco como par&#226;metro, passa-lo a uma factory para devolver o objeto com o conhecimento sobre o formato da linha no extrato (a classe tamb&#233;m dever&#225; receber a linha do extrato)
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
    <p>
      - Transa&#231;&#245;es do domingo passar para a segunda
    </p>
    <p>
      - Fazer uma tabela a parte para manter as string pelas quais uma conta pode ser referida no extrato.
    </p>
    <p>
      &#160;&#160;- Criar uma conta &quot;catch all&quot;, para quando uma linha do extrato n&#227;o bater com nenhuma das strings acima (isto justifica uma a&#231;&#227;o de update do lan&#231;amento)
    </p>
    <p>
      &#160;&#160;- Criar linhas nesta tabela com conta=null para indicar que a linha deve ser ignorada (&#250;til para linhas de saldo, por exemplo)
    </p>
    <p>
      
    </p>
    <p>
      - Linha no lan&#231;amento sem extrato: excluir linha do lan&#231;amento
    </p>
    <p>
      - linha do extrato sem lancamento correspondente: inserir no lancamento
    </p>
    <p>
      - linha do extrato com lancamento correspondente: atualiza o extratoid do lancamento
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277118690" FOLDED="true" ID="ID_1858757040" MODIFIED="1333071236690" POSITION="right" TEXT="Milestone 4.1">
<node CREATED="1326277137957" ID="ID_296700627" MODIFIED="1326277166720" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Simula&#231;&#227;o dos modelos completa
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277180145" ID="ID_250398468" MODIFIED="1326277952213" TEXT="lancmodelo-ls: Ordenar lista por dia de vencto"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277175167" ID="ID_847596701" MODIFIED="1326452749141" TEXT="lancmodelo-ls: Contabilizar saldo"/>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277220509" ID="ID_1321478390" MODIFIED="1326797297071" TEXT="lancmodelo-ls: Criar campos de porcentual">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Criar campos de porcentual em rela&#231;&#227;o ao total de despesas ou receitas (no caso de dinheiro que entra) e porcentuais acumulados
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277132680" ID="ID_1176934154" MODIFIED="1333071203397" POSITION="right" TEXT="Milestone 4.2">
<node CREATED="1326277305110" ID="ID_1431346685" MODIFIED="1326277326287" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Sincroniza&#231;&#227;o do extrato funcional
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277327650" ID="ID_64718160" MODIFIED="1329007468748" TEXT="Abstrair regras">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      A partir da planilha de sincroniza&#231;&#227;o manual, abstrair regras.
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1326277374326" ID="ID_1293980155" MODIFIED="1329007470674" TEXT="Ordernar lista">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Ordenar a lista pela data que o parser atribui.
    </p>
  </body>
</html></richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1329007329389" ID="ID_1583089983" MODIFIED="1329084509767" TEXT="C&#xe1;lculo do N para novos lan&#xe7;amentos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Ainda n&#227;o est&#225; calculando o N para lan&#231;amentos gerados durante a sincroniza&#231;&#227;o. Dever&#225; levar em conta lan&#231;amentos j&#225; existentes e os noos gerados durante o processo de sincronia.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1326277431201" ID="ID_513442912" MODIFIED="1329089803690" POSITION="right" TEXT="Milestone 5">
<node CREATED="1326277444694" ID="ID_1463337530" MODIFIED="1333071202112" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Funcionalidades de listar lan&#231;amentos, adicionar lan&#231;amento e adicionar lan&#231;amento full vers&#227;o web.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1333071205248" ID="ID_1946151865" MODIFIED="1333071219555" TEXT="Add Lancto - Web"/>
<node CREATED="1333071219994" ID="ID_1186624475" MODIFIED="1333071226059" TEXT="Add Lancto Full - Web"/>
<node CREATED="1333071226361" ID="ID_1838279097" MODIFIED="1333071231724" TEXT="Lista Lancto - Web"/>
<node CREATED="1333071249148" ID="ID_1702043462" MODIFIED="1333071252759" TEXT="Migrar para web"/>
<node CREATED="1336942693067" ID="ID_70465307" MODIFIED="1336943164242" TEXT="conta-setbalance">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Criar uma a&#231;&#227;o para alterar o balan&#231;o de uma conta em determinada data. Dever&#225; ser adicionada uma flag de balan&#231;o na tabela de conta e uma quantidade arbitr&#225;ria de contas poder&#227;o ter a flag de balan&#231;o ativa.
    </p>
    <p>
      Durante o c&#225;lculo dos saldos, realizado pelo Contabilizador, o valor de saldo j&#225; calculado dever&#225; ser desconsiderado caso a conta de origem ou destino esteja marcada com balan&#231;o. O novo valor de saldo da outra conta do lan&#231;amento dever&#225; ser, dai por diante, o do lan&#231;amento em quest&#227;o.
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
<node CREATED="1326277497865" ID="ID_1202848612" MODIFIED="1329007565760" POSITION="right" TEXT="Milestone 6">
<node CREATED="1326277501404" ID="ID_133246500" MODIFIED="1326277626870" TEXT="Objetivos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Fazer an&#225;lise do balan&#231;o:
    </p>
    <p>
      &#160;&#160;- Propor um lan&#231;amento de confer&#234;ncia quando o balan&#231;o n&#227;o bater com o saldo da conta
    </p>
    <p>
      &#160;- Mostrar um status especial quando o balan&#231;o do extrato bater com o saldo da conta, ou quando j&#225; houver um lancto de confer&#234;ncia que estiver ok
    </p>
    <p>
      &#160;&#160;- Criar a&#231;&#227;o de balan&#231;o, dever&#225; receber um saldo, uma conta e, opcionalmente, uma data.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1322738893562" FOLDED="true" ID="ID_1250441849" MODIFIED="1329089895240" POSITION="right" TEXT="Corre&#xe7;&#xf5;es">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Cadastrados na ordem em que devem ser atacados
    </p>
  </body>
</html></richcontent>
<node CREATED="1324252814195" ID="ID_242129029" MODIFIED="1324253148231" TEXT="extrato-analyse: lanctos em feriados">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Considerar os lan&#231;amentos em feriados e finais de semana como sendo no pr&#243;ximo dia &#250;til (fazer uma tabela para isso)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1324252659815" ID="ID_134672916" MODIFIED="1324253143245" TEXT="extrato-sync: par&#xe2;metro de data">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Criar par&#226;metro para sincronizar apenas at&#233; determinada data
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322738576183" ID="ID_1153208439" MODIFIED="1326277716096" TEXT="modelo simulate: corre&#xe7;&#xe3;o de bug">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - simula&#231;&#227;o de modelo: n&#227;o mostrar os lan&#231;amentos do modelo que j&#225; tiverem algum lan&#231;amento correspondente
    </p>
    <p>
      - so mostrar nas listagens lancamentos com um m&#234;s ou menos de idade (criar par&#226;metro de data para isso)
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1324252493893" ID="ID_1726085239" MODIFIED="1336942643532" POSITION="right" TEXT="Melhorias">
<node CREATED="1324252581143" ID="ID_938160062" MODIFIED="1324252601867" TEXT="extrato-analyse: id do lancto"/>
<node CREATED="1324252602809" ID="ID_723758160" MODIFIED="1324252628136" TEXT="extrato-analyse: a&#xe7;&#xe3;o sync"/>
<node CREATED="1324252693904" ID="ID_1351041782" MODIFIED="1324254926952" TEXT="extrato-sync: balan&#xe7;os">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Considerar os balan&#231;os, lan&#231;ar confer&#234;ncias baseado neles caso o saldo da conta na data em quest&#227;o n&#227;o bater
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1324236189541" ID="ID_161880305" MODIFIED="1324238003917" TEXT="Reduzir tempo de conex&#xe3;o">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Principais possibilidades para resolver este problema:
    </p>
    <p>
      &#160;&#160;- Fazer um programa externo que encapsule e gerencie a conex&#227;o com o banco de dados. Finalizaria a conex&#227;o mediante um timeout.
    </p>
    <p>
      &#160;&#160;- Mexer no CustomDAO para isso (est&#225; f&#225;cil, o problema &#233; que isso vai exigir que um programa
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322738416483" ID="ID_1354654228" MODIFIED="1323629690517" TEXT="Extens&#xe3;o do modelo simulate">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - modelo simulate: permitir mais de um par de modelo/database, anexar todos ao final como &#233; feito hoje.
    </p>
    <p>
      &#160;&#160;- Pode ser criado um parser de arrays para isso
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1324253249843" ID="ID_1072509882" MODIFIED="1324253277530" TEXT="lanc-add: restringir valores negativos"/>
<node CREATED="1324253389565" ID="ID_502361869" MODIFIED="1324253514864" TEXT="extrato-sync: observa&#xe7;&#xf5;es">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Ao sincronizar/gerar um lan&#231;amento na sincroniza&#231;&#227;o do extrato acrescentar a refer&#234;ncia do extrato na observa&#231;&#227;o (colocar antes algo como: [EXTRATO] IOF, por exemplo)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1324254266194" ID="ID_1482016609" MODIFIED="1324254353345" TEXT="nova a&#xe7;&#xe3;o: conta-check">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Criar uma nova a&#231;&#227;o para confer&#234;ncia de conta, dever&#225; receber como par&#226;metros a conta, data e valor. Dever&#225; lan&#231;ar uma nova confer&#234;ncia na data. Caso j&#225; exista, dever&#225; atualizar a confer&#234;ncia existente
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1326277629987" ID="ID_1203878710" MODIFIED="1326277651743" TEXT="lanc-add: mostrar saldo">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Mostrar o saldo da conta ap&#243;s uma inclus&#227;o de novo lan&#231;amento
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1326797327237" ID="ID_696808698" MODIFIED="1326797415665" TEXT="lancmodelo-ls: Ordena&#xe7;&#xe3;o por porcentual">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Receber um par&#226;metro que indique que a lista deve ser ordenada pelo porcentual de receita/despesa de cada lan&#231;amento ou pelo dia de vencimento (padr&#227;o).
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1327918700471" ID="ID_122078373" MODIFIED="1327918776740" TEXT="extrato-sync: mostrar resolu&#xe7;&#xe3;o da conta">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Criar uma coluna para mostrar se a refer&#234;ncia do extrato foi resolvida atrav&#233;s das refer&#234;ncias automaticas existentes no banco de dados ou se foi pelas refer&#234;ncias informadas pela linha de comando.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1329070235411" ID="ID_898986832" MODIFIED="1329070302304" TEXT="lanc-ls: Formato do saldo">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Melhorar a formata&#231;&#227;o do saldo que aparece ao final da listagem de lan&#231;amentos
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1329070308828" ID="ID_836758552" MODIFIED="1329070395233" TEXT="Tipo dos campos num&#xe9;ricos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Tipos dos campos num&#233;ricos: alterar de double para numeric com 2 casas de precis&#227;o. Ver se isso faz parar de acontecer aqueles problemas de arredondamento que aparecem ao formatar os n&#250;meros em tela.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1322272082887" ID="ID_1707116991" MODIFIED="1336942618699" POSITION="right" TEXT="Funcionalidades futuras">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Fazer modelos derivados. Ao criar um modelo, ele podera ser marcado como derivado de outro para herdar seus lancamentos
    </p>
  </body>
</html></richcontent>
<node CREATED="1322738319787" ID="ID_964412691" MODIFIED="1322738324271" TEXT="modelos derivados"/>
<node CREATED="1322738326482" ID="ID_1080856909" MODIFIED="1322738385788" TEXT="Listagem de lan&#xe7;amentos previstos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Listar os lan&#231;amentos previstos (com lancamentomodeloid &lt;&gt; null) e os n&#227;o previstos (lancamentomodeloid == null)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322738456790" ID="ID_1146040637" MODIFIED="1322738475360" TEXT="Flag de conta antiga">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Criar uma flag para conta antiga (n&#227;o devem aparecer nas listagens de contas, seus lan&#231;amentos n&#227;o aparecem nas listagens, nunca deveriam aparecer em lugar nenhum, a menos que explicitamente solicitado.
    </p>
    <p>
      &#160;&#160;- Contas sem lancamento ha mais de dois meses poderiam ser consideradas antigas.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1323133250802" ID="ID_1456365686" MODIFIED="1323133271318" TEXT="Contas aninhadas">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Permitir que uma conta tenha subcontas
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1322272446879" ID="ID_1717968237" MODIFIED="1336942628225" POSITION="right" TEXT="Funcionalidades n&#xe3;o-essenciais">
<node CREATED="1322272462078" ID="ID_1625700189" MODIFIED="1322272469429" TEXT="help">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      help
    </p>
    <p>
      &#160;&#160;backup
    </p>
    <p>
      &#160;&#160;conta
    </p>
    <p>
      &#160;&#160;lancamento
    </p>
    <p>
      &#160;&#160;modelo
    </p>
    <p>
      &#160;&#160;lancamentomodelo
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322272470433" ID="ID_1399344834" MODIFIED="1322272525181" TEXT="backup">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      --full
    </p>
    <p>
      --ddlonly
    </p>
    <p>
      
    </p>
    <p>
      Apenas rodar as linhas de comando. Talvez pedir a localiza&#231;&#227;o do psql em arquivo .ini
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322272525859" ID="ID_29405650" MODIFIED="1322272790395" TEXT="Arquivo de configura&#xe7;&#xe3;o">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Mover as configura&#231;&#245;es de conex&#227;o para&#160;&#160;um arquivo .properties (externo ao jar)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322738931393" ID="ID_769915033" MODIFIED="1322738952535" TEXT="Diminuir tempo de conex&#xe3;o com o banco">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Provavelmente &#233; alguma configura&#231;&#227;o no EclipseLink
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</map>
