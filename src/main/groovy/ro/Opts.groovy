package ro

/**
 * Created by zenghuiying on 2015/3/23.
 */
class Opts {
    static def get(args) {
        def opts = null
        def optsArr = []
        args.each {
            if (it instanceof LinkedHashMap) {
                it.keySet.each { e ->
                    opts[e] = it.get(e)
                }
                optsArr.putAt it
            }
        }
        return [args - optsArr, opts]
    }
}
