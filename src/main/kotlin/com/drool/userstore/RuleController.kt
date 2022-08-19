package com.drool.userstore

import com.myspace.userstore.UserStore
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rule", produces = ["application/json"])
class RuleController(
    private val ruleService: RuleService
) {

    // Checks whether user can purchase based on user status.
    // If User.status = true, then canPurchase= true
    // else canPurchase= false
    @PostMapping(value = ["/user-status"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun userStatusCheck(
        @RequestBody body: UserStore
    ): ResponseEntity<UserStore> {
        return ruleService.userStatusCheck(body)
    }

    // Checks whether Store has adequate amount of item.
    // If Store.quantity < 1, then Store.saleNotAllowed= true
    // else Store.saleNotAllowed= false
    @PostMapping(value = ["/item-quantity-status"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun itemQuantityCheck(
        @RequestBody body: UserStore
    ): ResponseEntity<UserStore> {
        return ruleService.itemQuantityCheck(body)
    }

    // Checks whether User can buy item from store based on age.
    // If User.age < 18 and Store.itemType = "alcohol", then user.canPurchase = false
    // else user.canPurchase = true
    @PostMapping(value = ["/age-based-purchase"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun userPurchaseBasedOnAge(
        @RequestBody body: UserStore
    ): ResponseEntity<UserStore> {
        return ruleService.userPurchaseBasedOnAge(body)
    }

}