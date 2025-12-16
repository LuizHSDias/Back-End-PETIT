package com.cefet.ds_petit.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID; // Para gerar nomes de arquivo únicos

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cefet.ds_petit.dto.ImagemDTO;
import com.cefet.ds_petit.entities.Imagem;
import com.cefet.ds_petit.repositories.ImagemRepository;



@Service
public class ImagemService {
    
    //pasta de upload do application.properties
	@Value("${app.upload.dir}") 
    private String uploadDir;
	
	@Autowired
    private ImagemRepository arquivoRepository;
    
    public ImagemService() {
    } 
    
    public ImagemDTO uploadImagem(MultipartFile arquivo) throws IOException {
        // 1. Validar o tipo de arquivo
        String tipoArquivo = arquivo.getContentType();
        if (tipoArquivo == null || (!tipoArquivo.equals("image/png") && !tipoArquivo.equals("image/jpeg"))) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado. Apenas PNG e JPG são permitidos.");
        }

        // 2. Gerar um nome de arquivo único para evitar colisões
        String nomeArquivoOriginal = arquivo.getOriginalFilename();
        String extensaoArquivo = "";
        if (nomeArquivoOriginal != null && nomeArquivoOriginal.contains(".")) {
        	extensaoArquivo = nomeArquivoOriginal.substring(nomeArquivoOriginal.lastIndexOf("."));
        }
        String nomeArquivoUnico = UUID.randomUUID().toString() + extensaoArquivo;
        
        Path pastaUpload = Paths.get(uploadDir);
        Files.createDirectories(pastaUpload); // Garante que o diretório existe
        Path caminhoArquivo = pastaUpload.resolve(nomeArquivoUnico);

        // 3. Salvar o arquivo no servidor
        Files.copy(arquivo.getInputStream(), caminhoArquivo);

        // 4. Salvar o registro no banco de dados
        Imagem imagem = new Imagem();
        imagem.setNome(nomeArquivoUnico); 
        Imagem imagemSalva = arquivoRepository.save(imagem);

        // 5. Retornar o DTO
        return new ImagemDTO(imagemSalva);
    }    
}