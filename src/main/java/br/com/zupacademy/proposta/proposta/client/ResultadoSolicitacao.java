package br.com.zupacademy.proposta.proposta.client;

import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicação;

public enum ResultadoSolicitacao {
    SEM_RESTRICAO {
        @Override
        public ResultadoAvalicação getResultado() {
            return ResultadoAvalicação.ELEGIVEL;
        }
    }, COM_RESTRICAO {
        @Override
        public ResultadoAvalicação getResultado() {
            return ResultadoAvalicação.NAO_ELEGIVEL;
        }
    };

    public abstract ResultadoAvalicação getResultado();
}
