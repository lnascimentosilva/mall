# Mall App

Um Shopping Center tem a necessidade de registrar as lojas que possui. Deve ser feito um sistema para permitir o cadastro e manutenção dos dados de uma loja, assim como informar quando ela deixou de fazer parte do shopping.

URLs: 
###### Listar segmentos
- **GET** http://localhost:8080/mall/api/segments

###### Incluir um segmento
- **POST** http://localhost:8080/mall/api/segments
```
{
	"name" : "Alimentação"
}
```

###### Alterar um segmento
- **PUT** http://localhost:8080/mall/api/segments/1
```
{
	"name" : "Livraria"
}
```

###### Encontrar um segmento pelo id
- **GET** http://localhost:8080/mall/api/segments/1

###### Listar lojas
- **GET** http://localhost:8080/mall/api/stores

###### Incluir uma loja
- **POST** http://localhost:8080/mall/api/stores
```
{
	"cnpj" : "61365284000104",
	"name" : "Livraria Saraiva",
	"floor" : "2º andar",
	"number" : "289",
	"init" : "2017-01-01",
	"segments" : [ 1 ]
}
```

###### Alterar uma loja
- **PUT** http://localhost:8080/mall/api/stores/1
```
{
	"cnpj" : "31004013000910",
	"name" : "Livraria da Travessa",
	"floor" : "3º andar",
	"number" : "387",
	"init" : "2016-01-01",
	"segments" : [ 1 ]
}
```

###### Encontrar pelo id
- **GET** http://localhost:8080/mall/api/stores/1


###### Deletar uma loja
- **DELETE** http://localhost:8080/mall/api/stores/1
