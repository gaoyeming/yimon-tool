if redis.call('setnx', KEYS[1], ARGV[1])  == 1 then
    return redis.call('expire', KEYS[1], ARGV[2]);
end
if redis.call('get', KEYS[1]) == ARGV[1] then
    return redis.call('expire', key, ARGV[2]);
else
    return 0;
end