package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        /* Para controlar a quantidade de registro exibido por pagina usar "URL?size=quantidade"
        exemplo: http://localhost:8080/medicos?size=2 */

        /* Para controlar a pagina de registro exibido usar "URL?page=numeroPagina"
        exemplo: http://localhost:8080/medicos?page=2 */

        /* Lembre-se de acrecentar "&"
        exemplo: http://localhost:8080/medicos?size=2&page=2  */

        /* Para Ordenar os registros acrescentar "URL?sort=nomeDoAtributo,asc/desc"
        * exemplo: http://localhost:8080/medicos?sort=nome,asc*/

        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

}
