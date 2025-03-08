package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }

    }

}
