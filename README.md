# ArquiteturaRestSpring

Olá, desenvolvedor. Esse projeto foi criado com a intenção de simplificar a criação de crud com spring. Com essa arquitetura você precisa apenas efetuar algumas configurações e seu CRUD estará pronto
com o insert, update, delete, findAll, findById e uma busca paginada, não será preciso criar nenhum desses metodos em seu controller. As chamadas serão executadas da forma default dos metodos HTTP.

Para utilizar é necessário implementar algumas coisas. 

Em sua entity é necessario extender o AbstractModel

Em seu DTO é necessário extender o AbstractDTO

-----------------------------------------------------------------Service-----------------------------------------------------------------------------
Necessário criar uma interface que será seu service, como por exemplo ProdutoService. Esse service irá extender extends ICrudService<SuaEntity>
você precisa criar uma implementação dessa interface que será o ProdutoServiceImpl por exemplo. Sua implementação irá extender de CrudServiceImpl<SuaEntitiy> e logicamente implementar seu Service.
A ClasseServiceImpl deve ser notada como @Service. É Nessa classe que você irá criar suas regras de negocio.

Você será obrigador a implementar o metodo getRepository, nele você irá retornar o seu repository que será injetado em seu seviceImpl.

-----------------------------------------------------------------Repository-----------------------------------------------------------------------------
Em seu repository você precisa extender apenas o JPARepository normalmente.

-----------------------------------------------------------------Controller-----------------------------------------------------------------------------
Em seu Controller é necessário extender o AbstractController<Entity, DTO>, ele exigirá que você implemente 3 metodos: 
  - getService: SuaInterfaceService.class
  - getModelClass: SuaEntity.class
  - getDTOClass: SeuDto.class
-----------------------------------------------------------------Configuration-----------------------------------------------------------------------------
Em seu projeto será necessário criar uma classe de configuração para registrar o Bean ModelMapper que é usado na arquitetura para efetuar a conversão de seus objetos.
Crie uma classe com nome de  sua escolha notada com o @Configuration e crie um metodo que retorno um new ModelMapper notado com @Bean

exemplo:

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper ();
    }
}

-----------------------------------------------------------------Uso-----------------------------------------------------------------------------
Para usar em seu projeto você pode baixar o projeto e importar em sua workspace e colocar as dependencias do mesmo em seu projeto.
	<dependency>
			<groupId>br.com.eduardo.spring.arquitetura</groupId>
			<artifactId>arquiteturaspring</artifactId>
			<version>0.0.2-SNAPSHOT</version>
	</dependency>

ou se preferir pode criar um jar do projeto e importar em seu maven localemnte com o comando
-Dfile= Caminho do seu jar
-DgroupId= groupId do projeto de arquitetura
-DartifactId= ArtifactId do projeto de arquitetura

exemplo:
mvn install:install-file -Dfile=C:\Documentos\desenvolvimento\workspaces\estudo\jar/arquiteturaspring.jar -DgroupId=br.com.eduardo.spring.arquitetura -DartifactId=arquiteturaspring -Dversion=0.0.2-SNAPSHOT -Dpackaging=jar -DgeneratePom=true

Teste no PostMan:

Seu Path + POST = Insert
Seu Path + PUT = Update
Seu Path/Id + GET = findById
Seu Path + GET = findAll
Seu Path/Id + DELETE = delete
