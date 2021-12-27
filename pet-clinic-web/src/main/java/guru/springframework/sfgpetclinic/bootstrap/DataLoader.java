package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
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
                .address("123 Brickerel")
                .city("Miami")
                .telephone("1231231234 ")
                .build();

        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .build();

        ownerService.save(owner2);

        Pet pet1 = petService.save(Pet.builder()
                .name("Roscoe")
                .petType(savedDogType)
                .birthDate(LocalDate.of(2018, 8, 8))
                .owner(owner1)
                .build());

        Pet pet2 = petService.save(Pet.builder()
                .name("Sally")
                .petType(savedCat)
                .birthDate(LocalDate.of(2020, 10, 2))
                .owner(owner2)
                .build());

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
