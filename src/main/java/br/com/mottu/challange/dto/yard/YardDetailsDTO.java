package br.com.mottu.challange.dto.yard;


import br.com.mottu.challange.entity.Yard;

public record YardDetailsDTO(Long id,
                             String name,
                             Long idUnit) {

    public YardDetailsDTO(Yard yard) {
        this(yard.getId(), yard.getName(), yard.getUnit().getId());
    }

}
