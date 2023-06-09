package com.ts.web.controller.enmus;

public enum EnmuPrivateKey {

    RSA_PRIVATE_KEY ("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMaoe/1HnKxvL98zl1kk05gaV5eDQN5qd45Rm2WHhikqouAtjwi9/xLHChgACn+jkkX3fC9ukDcxz51XOvz81Rj3AeGfRvq5ONr21clXplmfmJCYzHxr3lT7WF5pkyZ+Cf1zixp2959wkeOE9m0xt0MI2htJxgFX0KvJrX5W1Q09AgMBAAECgYAlg7umYNedWIHZzIxQMTwkBRV1bzvYxJI/Nfn6elVDgOGxI+lW5Uo96Yhmb9uLrv/4Znv1GRPy2F00gTyKedRjxTqWwNTBu6C9zPO/x26vTTFS7QIMkO5mmD+Vl2gSO+9lNkO0uVXvpJLP+rcxqJg3vQrRaYA+g3IeGX0UgHuMAQJBAOzqzVBJEtNGBIjTkuWOY5iPcdUw2y47DKc2P/HSWJYalrAwo3Ic99a9t8kdk7PPy3L0cE+QP6CPZJnq45g9eN0CQQDWqMlGUG2ZceXeAfMHY8cdsZP1pVxWusps0+QS25QR7wv2TX7bmBxhBJARFlF4Z4sA6SzKhFwzBaw4oWJdnm/hAkAnBFBFvZMNGkXa5sUVawq58D2193LRPUZLL2L+hz6xkbVpVMlGHMEwK1ReGbHedgUybFEmuUHRRmwx8P4Eb3M9AkEAgUXs0PemyX4aXPT3kz+3rl4/KPSJA4JCXf/Nnr22EobPwWkyOLUGgjbpPCEgt05EHCxQ2TWVAPUtnn0ybAb6gQJBALvi+nUGUD0EoqiVFpbq20l9Y4OtCwILKLGGJk0i/sc6UEHR+a7tfpySbqHqTFunZTz5cUwuWq7bFr5i7odWblM="),;

    private String value;

    EnmuPrivateKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
