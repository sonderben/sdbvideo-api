package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category,Long> {
}
