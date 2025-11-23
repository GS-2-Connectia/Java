package br.com.Connectia.bean;

import br.com.Connectia.dto.*;
import br.com.Connectia.dto.cursos.DetalhesCursoDto;
import br.com.Connectia.model.Curso;
import br.com.Connectia.model.Conteudo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ModelMapperProducer {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        // ============================================================
        // ENTIDADE → DTO
        // ============================================================

        // Curso → DetalhesCursoDto
        modelMapper.typeMap(Curso.class, DetalhesCursoDto.class)
                .addMappings(mapper -> {
                    mapper.map(Curso::getIdCurso, DetalhesCursoDto::setIdCurso);
                    mapper.map(Curso::getNome, DetalhesCursoDto::setNomeCurso);
                    mapper.map(Curso::getDescricao, DetalhesCursoDto::setDescricaoCurso);
                    mapper.map(Curso::getTipoConteudo, DetalhesCursoDto::setTipoConteudo);
                    mapper.map(Curso::getDataInicio, DetalhesCursoDto::setDataInicio);
                    mapper.map(Curso::getStatus, DetalhesCursoDto::setStatusCurso);
                    mapper.map(Curso::getIdUsuario, DetalhesCursoDto::setIdUsuario);
                    mapper.map(Curso::getIdArea, DetalhesCursoDto::setIdArea);
                });

        // Conteúdo → ConteudoDto
        modelMapper.typeMap(Conteudo.class, ConteudoDto.class)
                .addMappings(mapper -> {
                    mapper.map(Conteudo::getIdConteudo, ConteudoDto::setIdConteudo);
                    mapper.map(Conteudo::getTitulo, ConteudoDto::setTitulo);
                    mapper.map(Conteudo::getTipo, ConteudoDto::setTipo);
                    mapper.map(Conteudo::getIdCurso, ConteudoDto::setIdCurso);
                    mapper.map(Conteudo::getIdArea, ConteudoDto::setIdArea);
                });

        return modelMapper;
    }
}
