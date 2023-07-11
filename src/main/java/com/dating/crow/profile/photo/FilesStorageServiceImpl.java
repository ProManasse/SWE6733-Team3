package com.dating.crow.profile.photo;

import java.io.IOException;
import java.net.MalformedURLException;import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.dating.crow.controller.ProfileController;
import com.dating.crow.profile.model.Profile;
import com.dating.crow.profile.repository.ProfileRepository;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
  @Autowired
  private ProfileRepository profileRepository;
  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
	public void save(String id, MultipartFile file) {
		if (!profileRepository.findById(Long.parseLong(id)).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Profile not found");
		}
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
  
  @Override
	public void updateProfile(String id,List<String> names) {
	  Optional<Profile> up = profileRepository.findById(Long.parseLong(id));
		if (!up.isPresent()) {
			//Exception
		}
		Profile pe=up.get();
		String[] photos=new String[names.size()];
		int i=0;
		if(pe.getPhotos()!=null) {
			i=pe.getPhotos().length;	
		}
		System.out.println(i);
		for(String p:names) {
			photos[i]=MvcUriComponentsBuilder.fromMethodName(ProfileController.class, "getFile", p).build().toString();
			i++;
		}
		pe.setPhotos(photos);
		profileRepository.save(pe);
	}

}