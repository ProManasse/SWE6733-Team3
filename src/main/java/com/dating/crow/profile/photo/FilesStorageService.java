package com.dating.crow.profile.photo;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FilesStorageService {
	  public void init();

	  public void save(String username,MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
	  
	  public void updateProfile(String s,List<String> filenames);
}