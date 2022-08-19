package com.drool.userstore

import com.myspace.userstore.Store
import com.myspace.userstore.User
import com.myspace.userstore.UserStore
import org.kie.api.KieServices
import org.kie.api.command.Command
import org.kie.server.api.marshalling.MarshallingFormat
import org.kie.server.client.KieServicesConfiguration
import org.kie.server.client.KieServicesFactory
import org.kie.server.client.RuleServicesClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import mu.KotlinLogging


@Service
class RuleService(
    private val ruleConfig: RuleConfig
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private val format = MarshallingFormat.JSON
    private val userClassName ="User"
    private val storeClassName ="Store"

    fun runRule(body: UserStore, outputClassName: String): UserStore {
        try {
            val user= body.user
            val store= body.store
            val cmds: ArrayList<Command<*>> = ArrayList()
            val commands = KieServices.Factory.get().commands
            cmds.add(commands.newInsert(user, userClassName))
            cmds.add(commands.newInsert(store, storeClassName))
            cmds.add(commands.newFireAllRules())

            val conf: KieServicesConfiguration = KieServicesFactory.newRestConfiguration(
                ruleConfig.url,
                ruleConfig.user,
                ruleConfig.password)
            conf.marshallingFormat = format

            val client: RuleServicesClient = KieServicesFactory.newKieServicesClient(conf).getServicesClient(
                RuleServicesClient::class.java)

            val myCommands = commands.newBatchExecution(cmds)
            val response = client.executeCommandsWithResults(
                ruleConfig.containerId,
                myCommands)
            return if (outputClassName == userClassName) {
                val resp= response.result.getValue(outputClassName) as User
                UserStore(resp, store)
            } else {
                val resp= response.result.getValue(outputClassName) as Store
                UserStore(user, resp)
            }
        } catch (exception: Exception) {
            logger.error("Exception =====>", exception)
            return UserStore(null, null)
        }
    }

    fun userStatusCheck(body: UserStore): ResponseEntity<UserStore> {
        val resp = runRule(body, userClassName)
        return ResponseEntity.status(HttpStatus.OK).body(resp)
    }

    fun itemQuantityCheck(body: UserStore): ResponseEntity<UserStore> {
        val resp = runRule(body, storeClassName)
        return ResponseEntity.status(HttpStatus.OK).body(resp)
    }

    fun userPurchaseBasedOnAge(body: UserStore): ResponseEntity<UserStore> {
        val resp = runRule(body, userClassName)
        return ResponseEntity.status(HttpStatus.OK).body(resp)
    }
}