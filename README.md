# provaSprint4

AssociadoController.java:
- POST/associados:
  - Observações:
    - O campo "cargoPolitico" só aceita os valores "Vereador", "Prefeito", "Deputado Estadual", "Deputado Federal", "Senador", "Governador", "Presidente" e "nenhum",         sendo que a escrita do campo é case sensitive;
    - O campo "sexo" só aceita os valores "Masculino" e "Feminino" sendo que a escrita do campo é case sensitive; 
- POST/associados/partidos:
  - Vincula um associado a um partido, com o body como no exemplo abaixo:
    {
    "idAssociado": 1,
    "idPartido": 1
    }
- GET/associados:
  - Tem uma opção de filtrar associados de acordo com seu cargo político, como no exemplo:
    - http://localhost:8080/associados/?cargoPolitico=nenhum
  - Tem uma opção de ordenar por nome do associado de forma crescente:
    - http://localhost:8080/associados?ordenacao=nome
  - Tem uma opção de ordenar por nome do associado de forma decrescente:
    - http://localhost:8080/associados?ordenacao=nome&sentido=decrescente
- GET/associados/{id}:
  -   Filtra um associado pelo seu ID, como no exemplo:
    - http://localhost:8080/associados/1
- PUT/associados/{id}:
  - Atualiza um associado pelo seu Id, como no exemplo:
    - http://localhost:8080/associados/1
      -   {
          "nome": "Anamelia Aguiar",
          "cargoPolitico": "nenhum",
          "dataNascimento": "31/12/1960",
          "sexo": "Feminino"
          }
- DELETE/associados/{id}:
  - Delete determinado associado pelo seu ID, como no exemplo:
    - http://localhost:8080/associados/1
- DELETE/associados/{id}/partidos/{id}:
  - Desvincula determinado associado do partido ao qual ele está vinculado, como no exemplo:
    - http://localhost:8080/associados/5/partidos/5

PartidoController.java:
- POST/partidos:
  - Observação: o campo "ideologia" só aceita os valores "Esquerda", "Centro" e "Direita", sendo que a escrita do campo é case sensitive;
- GET/partidos:
  - Tem a opcão de filtrar os partidos por ideologia, como no exemplo:
    - http://localhost:8080/partidos?ideologia=Centro
- GET/partidos/{id}:
  - Filtra um partido de acordo com seu ID, como no exemplo:
    - http://localhost:8080/partidos/1
- GET/partidos/{id}/associados:
  - Lista todos os associados de determinado partido, como no exemplo:
    - http://localhost:8080/partidos/5/associados
- PUT/partidos/{id}:
  - Atualiza os dados de determinado partido, como no exemplo:
    - http://localhost:8080/partidos/1
      - {
        "nome": "Primeiro Partido",
        "sigla": "PP",
        "ideologia": "Direita",
        "dataFundacao": "14/07/2022"
        }
- DELETE/partidos/{id}:
  - Delete determinado partido de acordo com seu ID, como no exemplo:
    - http://localhost:8080/partidos/1
  
