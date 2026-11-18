# \# Offline-First Emergency Communication Network

# 

# Priority-Aware Limited Replication (PALR) for delay-tolerant emergency networks, built as a Java 21 + JavaFX simulator.

# 

# \## Pipeline

# \- Build order: \[docs/pipeline/build.pdf](docs/pipeline/build.pdf)

# \- Runtime flow: \[docs/pipeline/runtime.pdf](docs/pipeline/runtime.pdf)

# 

# \## Runtime flow

# 

# ```mermaid

# flowchart TD

# &#x20; U0(\["User launches the JavaFX app"]) --> U1\["Load config: buffer size, router, TTL, seed"]

# &#x20; U1 --> U2\["Load scenario CSV or generate one from the seed"]

# &#x20; U2 --> U3\["Build nodes with empty bounded stores"]

# &#x20; U3 --> U4\["Fill EventQueue with contacts and messages"]

# &#x20; U4 --> U5\["SimulationRunner starts paused on a background thread"]

# &#x20; U5 --> U6{"User presses Start?"}

# &#x20; U6 -- no --> U6

# &#x20; U6 -- yes --> L0

# 

# &#x20; subgraph LOOP\["Engine loop (background thread)"]

# &#x20;   L0\["Apply queued UI commands as events at time now"] --> L1\["Pop next event: earliest time, then type order"]

# &#x20;   L1 --> L2{"Event type?"}

# &#x20;   L2 -- CreateMessage --> M1\["Create Message with copy budget<br/>store original copy at source"]

# &#x20;   L2 -- ContactUp / SessionTick --> C0\["Run contact session"]

# &#x20;   L2 -- ContactDown --> D0\["Remove link from NetworkGraph"]

# &#x20;   L2 -- RestartNode --> R0\["Clear store if storage is volatile"]

# &#x20;   L2 -- End --> END0\["Stop run"]

# &#x20; end

# 

# &#x20; subgraph SESSION\["Contact session A-B (same code for every router)"]

# &#x20;   C0 --> C1\["Purge expired copies at A and B"]

# &#x20;   C1 --> C2\["Merge ack sets; purge copies already delivered"]

# &#x20;   C2 --> C3\["First session only: router updates its state"]

# &#x20;   C3 --> C4\["Router plans offers A to B and B to A"]

# &#x20;   C4 --> C5{"Transfers used < K<br/>and offers left?"}

# &#x20;   C5 -- no --> C9\["Session ends"]

# &#x20;   C5 -- yes --> C6{"Peer is destination?"}

# &#x20;   C6 -- yes --> C7\["DELIVER: record delivery,<br/>add ack, sender drops its copy"]

# &#x20;   C6 -- no --> C8{"Peer store admits copy?<br/>(evict a victim if full)"}

# &#x20;   C8 -- yes --> C10\["REPLICATE: commit token split,<br/>peer marks ID as seen"]

# &#x20;   C8 -- no --> C11\["Rejected: sender keeps copy and tokens"]

# &#x20;   C7 --> C5

# &#x20;   C10 --> C5

# &#x20;   C11 --> C5

# &#x20; end

# 

# &#x20; M1 --> N0

# &#x20; C9 --> N0

# &#x20; D0 --> N0

# &#x20; R0 --> N0

# &#x20; N0\["Listeners notified: MetricsCollector, event log"] --> N1\["Publish immutable snapshot"]

# &#x20; N1 --> L0

# &#x20; N1 -.-> UI1

# 

# &#x20; subgraph UI\["JavaFX thread"]

# &#x20;   UI1\["AnimationTimer reads latest snapshot"] --> UI2\["Redraw nodes, links, partitions, message paths"]

# &#x20;   UI2 --> UI3\["Update dashboard tiles and event log"]

# &#x20;   UI3 --> UI4{"User action?"}

# &#x20;   UI4 -- Send / connect / disconnect --> UI5\["Send Command to engine queue"]

# &#x20;   UI4 -- Pause / Reset --> UI6\["Runner pauses or rebuilds engine from same seed"]

# &#x20;   UI4 -- none --> UI1

# &#x20; end

# &#x20; UI5 -.-> L0

# 

# &#x20; END0 --> X1\["Compute ExperimentResult"]

# &#x20; X1 --> X2\["Export CSV and show results"]

# ```

