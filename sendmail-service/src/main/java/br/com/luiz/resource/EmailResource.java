package br.com.luiz.resource;

import br.com.luiz.response.AgendamentoResponse;
import br.com.luiz.response.CargaResponse;
import br.com.luiz.response.TransportadoraResponse;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mail")
public class EmailResource {

    @Inject
    Mailer mailer;

    @Path("/cancel")
    @POST
    public void emailAgendamentoCancelado(AgendamentoResponse agendamentoResponse) {
        var numeroAgendamento = agendamentoResponse.getNumero();
        var emails = retornaEmails(agendamentoResponse.getTransportadoras());
        var cargas = retornaCargas(agendamentoResponse.getCargas());

        mailer.send(
                Mail.withHtml(emails,
                        "Agendamento " + numeroAgendamento + " cancelado",
                        "<h2>As seguintes cargas estão presentes no agendamento:" + cargas + "</h2>"
                )
        );
    }

    @Path("/create")
    @POST
    public void emailAgendamentoCriado(AgendamentoResponse agendamentoResponse) {
        var numeroAgendamento = agendamentoResponse.getNumero();
        var emails = retornaEmails(agendamentoResponse.getTransportadoras());
        var cargas = retornaCargas(agendamentoResponse.getCargas());

        mailer.send(
                Mail.withHtml(emails,
                        "Agendamento " + numeroAgendamento + " criado",
                        "<h2>As seguintes cargas estão presentes no agendamento:" + cargas + "</h2>"
                )
        );
    }

    public String retornaEmails(List<TransportadoraResponse> transportadoras) {
        var emailsList = transportadoras.stream().map(TransportadoraResponse::getEmail)
                .collect(Collectors.toList());
        StringBuilder emails = new StringBuilder();
        for (String email : emailsList) {
            emails.append(email);
        }
        return emails.toString().replace("[", "").replace("]", "");
    }

    public List<String> retornaCargas(List<CargaResponse> cargas) {
        return cargas.stream().map(CargaResponse::getDescricao)
                .collect(Collectors.toList());
    }

}
