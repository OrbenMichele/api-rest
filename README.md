

Instruções de utilização da aplicação

1. Baixe a imagem (apenas na primeira vez): `docker pull postgres`
2. Execute o postgres: `docker run -p 5432:5432 --name my-postgres-server --env POSTGRES_PASSWORD=postgres -d postgres`
3. Execute mvn install na pasta do projeto
4. Verifique se a porta 9000 já está em uso
5. Utilize a collection adicionada ao git para efetuar as requisições.
6. É necessário solicitar o token através de usuário e senha. Fique tranquilo, este usuário é criado automaticamente junto com os scripts do banco que são executados quando carregar a aplicação pela primeira vez.
7. Execute a request Get Token que está dentro da pasta token.
8. Escolha uma request na pasta Persona ou House e infore o token recebido na aba Authorization.
9. Com a aplicação UP consulte a documentação da API em: http://localhost:9000/swagger-ui.html#/
9. Divirta-se com a inclusão dos seus personagens favoritos ou crie novos personagens a partir da sua imaginação :)

Foi um prazer criar essa aplicação, obrigada por utilizar. Ficarei feliz em receber feedbacks ;)



Aplicação desenvolvida em Java 8, String Boot, docker, postgres, oauth2 by Michele Orben - Abril,2020 - Florianópolis/Brasil

