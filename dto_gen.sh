#!/bin/bash

read -p "Enter DTO base name (e.g. Customer): " name

dir1="application/src/main/java/com/crm/dto/requestDtos"
dir2="application/src/main/java/com/crm/dto/responseDtos"
dir3="application/src/main/java/com/crm/repository"

file_name1="${dir1}/${name}RequestDto.java"
file_name2="${dir2}/${name}ResponseDto.java"
file_name3="${dir3}/${name}Repository.java"

# Create directories if not exist
mkdir -p "$dir1"
mkdir -p "$dir2"
mkdir -p "$dir3"
# Generate Request DTO
cat > "$file_name1" <<EOL
package com.crm.dto.requestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ${name}RequestDto {

}
EOL

# Generate Response DTO
cat > "$file_name2" <<EOL
package com.crm.dto.responseDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ${name}ResponseDto {

}
EOL

# Generate Repository interface
cat > "$dir3" <<EOL
package com.crm.repository;

import com.crm.model.${name};
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${name}Repository extends JpaRepository<${name}, Long> {
}
EOL

echo "✅ Created:"
echo "  - $file_name1"
echo "  - $file_name2"

