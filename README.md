# provaSprint4

AssociadoController:
- POST/associados:
  - Observação: O campo "cargoPolitico" só aceita os valores "Vereador", "Prefeito", "Deputado Estadual", "Deputado Federal", "Senador", "Governador", "Presidente" e "nenhum", sendo que a escrita do campo é case sensitive;
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
