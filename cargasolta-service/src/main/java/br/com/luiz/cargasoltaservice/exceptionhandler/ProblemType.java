package br.com.luiz.cargasoltaservice.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontada", "Entidade nao encontada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados_invalidos", "Dados Invalidos"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://localhost:8000" + path;
        this.title = title;
    }
}
