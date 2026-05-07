package com.cefet.ds_petit.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.entities.Emprestimo;
import com.cefet.ds_petit.repositories.EmprestimoRepository;

@Service
public class NotificacaoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private EmailService emailService;

   @Scheduled(cron = "0 0 8 * * *")
public void verificarPrazos() {

    List<Emprestimo> lista =
            repository.findAll();

    LocalDate amanha =
            LocalDate.now().plusDays(1);

    for (Emprestimo e : lista) {

        if (
            e.getPrazoMaximo() != null
            &&
            e.getPrazoMaximo().equals(amanha)
        ) {

            emailService.enviarEmail(

                e.getEstudante().getEmail(),

                "Prazo terminando",

                "Olá "
                + e.getEstudante().getNome()
                + ", o prazo do livro '"
                + e.getLivro().getTitulo()
                + "' termina amanhã."

            );

        }

    }

}

}
