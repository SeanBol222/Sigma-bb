package com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output;

import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.application.ports.output.BrandPersistencePort;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.domain.Brand;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.entities.BrandEntity;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.errors.BrandNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.mapper.BrandPersistenceMapper;
import com.bolivar.bioingenieria.app.sigma_bb.equipment_hexagon.infrastructure.output.repository.SpringBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BrandPersistenceAdapter implements BrandPersistencePort {
    private final SpringBrandRepository springBrandRepository;
    private final BrandPersistenceMapper brandPersistenceMapper;

    @Autowired
    public BrandPersistenceAdapter(SpringBrandRepository springBrandRepository,
                                    BrandPersistenceMapper brandPersistenceMapper) {
        this.springBrandRepository = springBrandRepository;
        this.brandPersistenceMapper = brandPersistenceMapper;
    }

    @Override
    public List<Brand> findAll() {
        List<BrandEntity> brandEntities = springBrandRepository.findAll();
        return brandEntities.stream()
                .map(brandPersistenceMapper::toBrand).toList();
    }

    @Override
    public Optional<Brand> findById(String id) {
        return springBrandRepository.findById(id)
                .map(brandPersistenceMapper::toBrand);
    }

    @Override
    public Brand save(Brand brand) {
        BrandEntity brandEntity = brandPersistenceMapper.toBrandEntity(brand);
        BrandEntity brandEntitySaved = springBrandRepository.save(brandEntity);
        return brandPersistenceMapper.toBrand(brandEntitySaved);
    }

    @Override
    public Brand update(String id, Brand brand) {
        BrandEntity existing = springBrandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));

        brandPersistenceMapper.updateEntityFromDomain(brand, existing);

        BrandEntity saved = springBrandRepository.save(existing);

        return brandPersistenceMapper.toBrand(saved);
    }

    @Override
    public void delete(String id) {
        if (!springBrandRepository.existsById(id)) {
            throw new BrandNotFoundException(id);
        }
        springBrandRepository.deleteById(id);
    }
}
