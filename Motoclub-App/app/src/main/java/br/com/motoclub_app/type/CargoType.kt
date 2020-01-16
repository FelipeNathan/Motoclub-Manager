package br.com.motoclub_app.type

enum class CargoType constructor(val description: String){

    ADM("Administrador(a)"),
    PRE("Presidente"),
    VPR("Vice-Presidente"),
    TSO("Tesoureiro(a)"),
    SEC("Secretário(a)"),
    INT("Integrante");

    companion object {

        fun getByDescription(desc: String) : CargoType? {

            for (t in values()) {
                if (t.description == desc)
                    return t
            }

            throw Exception("Cargo não definido")
        }
    }
}