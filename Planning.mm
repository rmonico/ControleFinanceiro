<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1322269798993" ID="ID_1526915694" MODIFIED="1322272365576" TEXT="jfin - Planning">
<node BACKGROUND_COLOR="#99ff99" CREATED="1322269815461" FOLDED="true" ID="ID_1671579002" MODIFIED="1322875338262" POSITION="right" TEXT="Milestone 1">
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
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270133624" FOLDED="true" ID="ID_209304459" MODIFIED="1322875335983" POSITION="right" TEXT="Milestone 2">
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
<node CREATED="1322270400623" ID="ID_674416975" MODIFIED="1322616124593" POSITION="right" TEXT="Milestone 3">
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
<node BACKGROUND_COLOR="#99ff99" CREATED="1322270725708" FOLDED="true" ID="ID_623657749" MODIFIED="1323185954525" TEXT="jfin extrato import ">
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
</html>
</richcontent>
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
</html>
</richcontent>
</node>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323182420467" ID="ID_1672761856" MODIFIED="1323185948318" TEXT="Atualiza&#xe7;&#xe3;o da importa&#xe7;&#xe3;o de extrato"/>
</node>
<node CREATED="1322271284140" ID="ID_1921731818" MODIFIED="1323181991587" TEXT="jfin extrato analyse">
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
      - Terminar de fazer a an&#225;lise manualmente
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
      - Colocar um indice &#250;nico na FK de lancamento para extrato (uma linha do extrato s&#243; pode ser referida por um lancamento)
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
</html>
</richcontent>
<node BACKGROUND_COLOR="#99ff99" CREATED="1323181709546" ID="ID_252868032" MODIFIED="1323186526367" TEXT="Altera&#xe7;&#xf5;es no Banco de Dados">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ReferenciaExtrato
    </p>
    <p>
      &#160;- id: int (PK)
    </p>
    <p>
      &#160;- ContaID: Conta
    </p>
    <p>
      &#160;- BancoID: Conta&#160;(not null)
    </p>
    <p>
      &#160;- referencia: string (not null)
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
    <p>
      - Colocar um indice &#250;nico na FK de lancamento para extrato (uma linha do extrato s&#243; pode ser referida por um lancamento)
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1323182526624" ID="ID_1557372692" MODIFIED="1323185993131" TEXT="Altera&#xe7;&#xf5;es no Modelo de Dados">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Criar Entidade para a ReferenciaExtrato (sem DAO)
    </p>
    <p>
      - Na Conta, criar:
    </p>
    <p>
      public List&lt;String&gt; getReferenciaExtratoList(Conta banco);
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1323182240989" ID="ID_1089707737" MODIFIED="1323186006796" TEXT="Codifica&#xe7;&#xe3;o da Action"/>
</node>
</node>
<node CREATED="1322738893562" FOLDED="true" ID="ID_1250441849" MODIFIED="1323182292069" POSITION="right" TEXT="Milestone 4">
<node CREATED="1322738416483" ID="ID_1354654228" MODIFIED="1322738431230" TEXT="Extens&#xe3;o do modelo simulate">
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
<node CREATED="1322738576183" ID="ID_1153208439" MODIFIED="1322738610579" TEXT="modelo simulate: corre&#xe7;&#xe3;o de bug">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - simula&#231;&#227;o de modelo: n&#227;o mostrar os lan&#231;amentos do modelo que j&#225; tiverem algum lan&#231;amento correspondente
    </p>
    <p>
      - so mostrar nas listagens lancamentos com um m&#234;s ou menos de idade
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1322738611754" ID="ID_1966233343" MODIFIED="1322738620103" TEXT="Filtro de Saldos">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      - Nas listagens mostrar o saldo apenas para contas contabiliz&#225;veis.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1323131189291" ID="ID_526244665" MODIFIED="1323131222855" TEXT="Testar campos money">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Ver se os campos money do postgres tem problema de arredondamento de ponto flutuante
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1322272082887" FOLDED="true" ID="ID_1707116991" MODIFIED="1323182293757" POSITION="right" TEXT="Funcionalidades futuras">
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
<node CREATED="1322272446879" FOLDED="true" ID="ID_1717968237" MODIFIED="1322738953629" POSITION="right" TEXT="Funcionalidades n&#xe3;o-essenciais">
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
