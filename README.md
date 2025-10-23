# Desafio Banco - API de Agências

API REST em **Java 17 + Spring Boot** com autenticação **OAuth2**, cache de agências e cálculo de distâncias.

---

## 🔹 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security + OAuth2 Resource Server  
- Banco H2 (em memória, pode ser trocado por MySQL/PostgreSQL)  
- Cache Caffeine  

---

## 🚀 Como Rodar

Importar a collection para o Postman que está dentro de resources.

Após importar a collection para o Postman, seguir os passos:
Executar os Endpoints na seguinte ordem:

1 - Criar usuario User
http://localhost:8081/usuarios

2 - Autorizar User
http://localhost:8081/auth

3 - Copiar o retorno do JWT Token retornado do Endpoint (Autorizar User)

4 - Criar Agencia 1 - Colar o Token que está copiado no Authorization, Bearer Token
http://localhost:8081/desafio/cadastrar

{
    "nome": "Santander Osasco",
    "posX": "100",
    "posY": "80"
}

5 - Criar Agencia 2 - Colar o Token que está copiado no Authorization, Bearer Token
http://localhost:8081/desafio/cadastrar

{
    "nome": "Santander Barueri",
    "posX": "-50",
    "posY": "10"
}

6 - Criar Agencia 3 - Colar o Token que está copiado no Authorization, Bearer Token
http://localhost:8081/desafio/cadastrar

{
    "nome": "Santander Santana de Parnaiba",
    "posX": "800",
    "posY": "5"
}

7 - Buscar distancia das Agencias - Colar o Token que está copiado no Authorization, Bearer Token
http://localhost:8081/desafio/distancia?posX=-5&posY=10

Os endpoints só irão funcionar se o User estiver autenticado.
O únicos endpoints que funcionam sem autenticação é o Criar usuario User e Criar usuario Admin.
