# Mini Autorizador

Instalação
----------
É necessário o maven instalado (apt-get install maven)

1. `mvn install` para instalar as dependências
2. `mvn spring-boot:run` para rodar a aplicação
3. Acesse a url: [http://localhost:8080](http://localhost:8080)


Tecnologias
-----------
Java 11, Spring-Boot, Spring Data


Descrição:
-----------

  Sua tarefa será construir um *mini-autorizador*. Este será uma aplicação Spring Boot com interface totalmente REST que permita:

 * a criação de cartões (todo cartão deverá ser criado com um saldo inicial de R$500,00)
 * a obtenção de saldo do cartão
 * a autorização de transações realizadas usando os cartões previamente criados como meio de pagamento

## Regras de autorização a serem implementadas

Uma transação pode ser autorizada se:
   * o cartão existir
   * a senha do cartão for a correta
   * o cartão possuir saldo disponível

Caso uma dessas regras não ser atendida, a transação não será autorizada.

## Demais instruções

O projeto contém um docker-compose.yml com 1 banco de dados relacional e outro não relacional.
Sinta-se à vontade para utilizar um deles. Se quiser, pode deixar comentado o banco que não for utilizar, mas não altere o que foi declarado para o banco que você selecionou. 

Não é necessário persistir a transação. Mas é necessário persistir o cartão criado e alterar o saldo do cartão caso uma transação ser autorizada pelo sistema.

Serão analisados o estilo e a qualidade do seu código, bem como as técnicas utilizadas para sua escrita. Ficaremos felizes também se você utilizar testes automatizados como ferramenta auxiliar de criação da solução.

Também, na avaliação da sua solução, serão realizados os seguintes testes, nesta ordem:

 * criação de um cartão
 * verificação do saldo do cartão recém-criado
 * realização de diversas transações, verificando-se o saldo em seguida, até que o sistema retorne informação de saldo insuficiente
 * realização de uma transação com senha inválida
 * realização de uma transação com cartão inexistente

-----------
## Url Swagger UI
http://localhost:8080/swagger-ui.html

