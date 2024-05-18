package com.api.cobroking.domain.property

import com.api.cobroking.base.BaseService
import com.api.cobroking.base.exception.PropertyNotFoundException
import com.api.cobroking.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyService(private val propertyRepository:  PropertyRepository): BaseService<PropertyDto> {

    override fun create(dto: PropertyDto): PropertyDto {

        var newAmenities = Amenities(
            null,
            null,
            dto.amenities?.balcony,
            dto.amenities?.pool,
            dto.amenities?.laundry,
            dto.amenities?.eventSpace,
            dto.amenities?.gym,
            dto.amenities?.openView,
            dto.amenities?.garage,
            dto.amenities?.airConditioning,
            dto.amenities?.expenses,
            dto.amenities?.propertyTax,
            dto.amenities?.water,
            dto.amenities?.cable,
            dto.amenities?.internet,
            dto.amenities?.gas,
            dto.amenities?.bedLinens,
            dto.amenities?.security
        )

        var newPhotos = dto.photos?.map {  return@map getNewPhotoFromDto(it) }

        var newProperty = Property(
            null,
            dto.description,
            dto.location,
            dto.size,
            dto.internalSize,
            dto.sizeUnit,
            dto.bathroomQty,
            dto.roomQty,
            newAmenities,
            newPhotos
        )
        var savedProperty: Property =
            propertyRepository.save(newProperty)
        return savedProperty.toPropertyDto()
    }

    override fun update(id: Long, dto: PropertyDto): PropertyDto {
        lateinit var dbProperty: Property
        try {
            dbProperty = propertyRepository.getReferenceById(id)
        } catch (e: EntityNotFoundException) {
            throw PropertyNotFoundException()
        }

        // Photo parsing
        var newPhotoList: MutableList<PropertyPhoto> = dto.photos?.filter { return@filter it.id == null }
            ?.map { return@map getNewPhotoFromDto(it) }?.toMutableList()!!

        var updatedPhotoMap: Map<Long?, PropertyPhotoDto> = dto.photos?.associateBy { it.id }!!
        var updatedPhotoList: MutableList<PropertyPhoto> = dbProperty.photos
            ?.filter { return@filter updatedPhotoMap.contains(it.id) }
            ?.map {  return@map it.updateFromDto(updatedPhotoMap[it.id]!!)}?.toMutableList()!!

        // Deleted elements are not in the list anymore
        val currentPhotoList: MutableList<PropertyPhoto> = mutableListOf()
        currentPhotoList.addAll(updatedPhotoList)
        currentPhotoList.addAll(newPhotoList)

        dbProperty.photos = currentPhotoList
        dbProperty.amenities?.updateFromDto(dto.amenities!!)

        var savedProperty: Property =
            propertyRepository.save(dbProperty.updateFromDto(dto))
        return savedProperty.toPropertyDto()
    }

    override fun getById(id: Long): PropertyDto {
        try {
            return this.propertyRepository.getReferenceById(id).toPropertyDto()
        } catch (e: EntityNotFoundException) {
            throw PropertyNotFoundException()
        }
    }

    override fun deleteById(id: Long) {
        this.propertyRepository.deleteById(id)
    }

    override fun getAll(): List<PropertyDto> {
        return this.propertyRepository.findAll().map { return@map it.toPropertyDto() }
    }

    private fun getNewPhotoFromDto(dto: PropertyPhotoDto): PropertyPhoto {
        return PropertyPhoto(
            null,
            null,
            dto?.text,
            dto?.url,
            dto?.isPrincipal
        )
    }
}