package app.repository

import app.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Organization, Int> {
    fun findByDepth(depth: Int?): List<Organization>
}