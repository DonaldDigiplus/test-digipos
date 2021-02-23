package com.devsdg.digipos.Cloudinary.Repository;

import com.devsdg.digipos.Cloudinary.Models.Cloudinarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepository extends JpaRepository<Cloudinarie, Long> {
}
