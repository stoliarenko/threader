package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Returns stub user"

    request {
        method GET()
        url "/users/session/1"
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: "user-id",
                name: "user-name",
                roles: ["COMMON"]
        )
    }
}