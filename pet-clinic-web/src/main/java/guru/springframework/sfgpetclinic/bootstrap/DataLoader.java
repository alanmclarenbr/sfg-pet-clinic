package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {

        PetType dog = PetType.builder()
                .name("Dog")
                .build();
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = PetType.builder()
                .name("Cat")
                .build();
        PetType savedCat = petTypeService.save(cat);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .build();

        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .build();

        ownerService.save(owner2);

        System.out.println("Loading owners.");

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .build();
        vetService.save(vet2);

        System.out.println("Loading Vets.");
    }
}
