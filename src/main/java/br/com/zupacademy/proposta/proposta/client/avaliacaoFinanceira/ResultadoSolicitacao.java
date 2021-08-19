package br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira;

import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicacao;

public enum ResultadoSolicitacao {
    SEM_RESTRICAO {
        @Override
        public ResultadoAvalicacao getResultado() {
            return ResultadoAvalicacao.ELEGIVEL;
        }
    }, COM_RESTRICAO {
        @Override
        public ResultadoAvalicacao getResultado() {
            return ResultadoAvalicacao.NAO_ELEGIVEL;
        }
    }, SEM_RESPOSTA {
        @Override
        public ResultadoAvalicacao getResultado() {
            return ResultadoAvalicacao.EM_ESPERA;
        }
    }, CARTAO_ENCONTRADO {
        @Override
        public ResultadoAvalicacao getResultado() {
            return ResultadoAvalicacao.APROVADO;
        }
    };

    public abstract ResultadoAvalicacao getResultado();
}
