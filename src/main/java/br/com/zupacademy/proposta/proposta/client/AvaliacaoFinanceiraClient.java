package br.com.zupacademy.proposta.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AvaliacaoFinanceira", url= "http://localhost:9999/api" )
public interface AvaliacaoFinanceiraClient {
    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    AvaliacaoFinanceiraResponse avalia(@RequestBody AvaliacaoFinanceiraRequest request);
}
