package com.minhaempresa.eletric_car.data

import com.minhaempresa.eletric_car.domain.Carro
//
object CarFactory {

    var list = listOf(
        Carro(
            id = 1,
            preco = "R$ 300.000,00",
            bateria = "300 kWh",
            potencia = "200 cv",
            recarga = "30 min",
            urlPhoto = "www.google.com.br"
        ),
        Carro(
            id = 2,
            preco = "R$ 200.000,00",
            bateria = "200 kWh",
            potencia = "150 cv",
            recarga = "40 min",
            urlPhoto = "www.google.com.br"
        ),
        Carro(
            id = 3,
        preco = "R$ 100.000,00",
        bateria = "200 kWh",
        potencia = "100 cv",
        recarga = "50 min",
        urlPhoto = "www.google.com.br"
    )

    )
}
//Verbos HTTP
// - GET -> Pare recuperar informações
// -POST -> Para enviar informações para um servidor
// -DELETE -> Que para deletar algum recurso
// -PUT -> Alterar uma entidade como um todo
// -PATCH -> Alterar um atributo da entidade
