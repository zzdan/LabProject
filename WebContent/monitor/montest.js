var a =
{
	"health" :
	{
		"health" :
		{
			"health_services" : [
			{
				"mons" : [
				{
					"name" : "monitor",
					"kb_total" : 82536112,
					"kb_used" : 7718320,
					"kb_avail" : 71431672,
					"avail_percent" : 86,
					"last_updated" : "2016-10-30 02:08:24.568718",
					"store_stats" :
					{
						"bytes_total" : 23806915,
						"bytes_sst" : 0,
						"bytes_log" : 874524,
						"bytes_misc" : 22932391,
						"last_updated" : "0.000000"
					},
					"health" : "HEALTH_OK"
				} ]
			} ]
		},
		"timechecks" :
		{
			"epoch" : 1,
			"round" : 0,
			"round_status" : "finished"
		},
		"summary" : [],
		"overall_status" : "HEALTH_OK",
		"detail" : []
	},
	"fsid" : "5fc084ce-f72b-4799-98e4-a6ed47b6fea8",
	"election_epoch" : 1,
	"quorum" : [ 0 ],
	"quorum_names" : [ "monitor" ],
	"monmap" :
	{
		"epoch" : 1,
		"fsid" : "5fc084ce-f72b-4799-98e4-a6ed47b6fea8",
		"modified" : "0.000000",
		"created" : "0.000000",
		"mons" : [
		{
			"rank" : 0,
			"name" : "monitor",
			"addr" : "10.0.0.7:6789\/0"
		} ]
	},
	"osdmap" :
	{
		"osdmap" :
		{
			"epoch" : 78,
			"num_osds" : 4,
			"num_up_osds" : 4,
			"num_in_osds" : 4,
			"full" : false,
			"nearfull" : false,
			"num_remapped_pgs" : 0
		}
	},
	"pgmap" :
	{
		"pgs_by_state" : [
		{
			"state_name" : "active+clean",
			"count" : 248
		} ],
		"version" : 239815,
		"num_pgs" : 248,
		"data_bytes" : 291499635,
		"bytes_used" : 39316439040,
		"bytes_avail" : 284881928192,
		"bytes_total" : 338067914752
	},
	"mdsmap" :
	{
		"epoch" : 1,
		"up" : 0,
		"in" : 0,
		"max" : 0,
		"by_rank" : []
	}
};
/*var json = JSON.parse(a);
alert("here");*/
alert(a.fsid);
